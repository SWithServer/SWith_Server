package com.example.demo.src.service;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.dto.request.PatchAttendanceReq;
import com.example.demo.src.dto.response.*;
import com.example.demo.src.entity.*;
import com.example.demo.src.repository.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final SessionRepository sessionRepository;
    private final GroupInfoRepository groupInfoRepository;
    private final UserRepository userRepository;
    private final RegisterRepository registerRepository;

    public AttendanceService(AttendanceRepository attendanceRepository, SessionRepository sessionRepository, GroupInfoRepository groupInfoRepository, UserRepository userRepository, RegisterRepository registerRepository) {
        this.attendanceRepository = attendanceRepository;
        this.sessionRepository = sessionRepository;
        this.groupInfoRepository = groupInfoRepository;
        this.userRepository = userRepository;
        this.registerRepository = registerRepository;
    }

    public GetGroupAttendanceRes getGroupAttendance(Long groupIdx) throws BaseException {
        GroupInfo groupInfo = groupInfoRepository.findById(groupIdx)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.INVALID_GROUP));

        List<UserAttendanceInfo> userTotalAttendance = attendanceRepository.getUserTotalAttendance(groupIdx);
        if (userTotalAttendance.isEmpty())
            throw new BaseException(BaseResponseStatus.NO_GROUP_ATTENDANCE);

        List<GetUserAttendanceRes> getUserAttendanceResList = new ArrayList<>();
        UserAttendanceInfo first = userTotalAttendance.get(0);
        Long prevId = first.getUserIdx();
        String nickname = first.getNickname();
        int totalAttendance = 0, validAttendance = 0;

        for (UserAttendanceInfo attend : userTotalAttendance) {
            Integer status = attend.getStatus();
            Integer attendanceNum = attend.getAttendanceNum();

            //다른 유저의 출석으로 넘어왔을 경우 출석율을 계산
            if (!attend.getUserIdx().equals(prevId)) {
                int attendanceRate;
                if (totalAttendance == 0)        //status 가 0(예정)인 출석 정보만 있을 경우
                    attendanceRate = -1;
                else
                    attendanceRate = validAttendance * 100 / totalAttendance;


                GetUserAttendanceRes userAttendanceRes = GetUserAttendanceRes.builder()
                        .nickname(nickname)
                        .attendanceRate(attendanceRate)
                        .userIdx(prevId)
                        .build();

                getUserAttendanceResList.add(userAttendanceRes);

                prevId = attend.getUserIdx();
                nickname = attend.getNickname();
                totalAttendance = 0;
                validAttendance = 0;
            }
            //마지막일 경우
            if (attend.equals(userTotalAttendance.get(userTotalAttendance.size() - 1))) {
                if (status == 1) {    //유효한 출석일 경우
                    totalAttendance += attendanceNum;
                    validAttendance += attendanceNum;
                } else if (status != 0) { // 2 혹은 3일 경우
                    totalAttendance += attendanceNum;
                }
                int attendanceRate;
                if (totalAttendance == 0)        //status 가 0(예정)인 출석 정보만 있을 경우
                    attendanceRate = -1;
                else
                    attendanceRate = validAttendance * 100 / totalAttendance;


                GetUserAttendanceRes userAttendanceRes = GetUserAttendanceRes.builder()
                        .nickname(nickname)
                        .attendanceRate(attendanceRate)
                        .userIdx(prevId)
                        .build();

                getUserAttendanceResList.add(userAttendanceRes);
            }

            if (status == 1) {    //유효한 출석일 경우
                totalAttendance += attendanceNum;
                validAttendance += attendanceNum;
            } else if (status != 0) { // 2 혹은 3일 경우
                totalAttendance += attendanceNum;
            }
        }


        GetGroupAttendanceRes getGroupAttendanceRes = GetGroupAttendanceRes.builder()
                .attendanceValidTime(groupInfo.getAttendanceValidTime())
                .getUserAttendanceResList(getUserAttendanceResList)
                .build();
        return getGroupAttendanceRes;
    }

    public Integer updateAttendance(Long userIdx, Long sessionIdx) throws BaseException {
        User user = userRepository.findById(userIdx)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.INVALID_USER));
        Session session = sessionRepository.findByIdWithGroup(sessionIdx)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.INVALID_SESSION));
        ///User가 가입 했는지 Validation 필요
        int checker = 0;
        for (Register register : user.getRegisterList()) {
            if (register.getGroupInfo() == session.getGroupInfo()) {
                checker++;
                break;
            }
        }
        //가입정보가 없을 경우 오류를 반환,
        if (checker == 0)
            throw new BaseException(BaseResponseStatus.NO_REGISTRATION_INFO);


        //출석정보가 없으면 만든다.
        Attendance attendance = attendanceRepository.findByUserAndSession(userIdx, sessionIdx)
                .orElseGet(() -> makeAttendanceInfo(user, session));
        //지금 시간과 출석 유효시간을 고려해 상태를 결정
        Integer status = decideStatus(session);
        //이미 출석정보가 있으면 오류(일반 사용자의 접근이기 때문)
        if (attendance.getStatus() != 0)
            throw new BaseException(BaseResponseStatus.ALREADY_ATTENDED);


        attendance.setStatus(status);
        attendanceRepository.save(attendance);

        return status;
    }

    private Attendance makeAttendanceInfo(User user, Session session) {

        Attendance attendance = Attendance.builder()
                .session(session)
                .groupInfo(session.getGroupInfo())
                .user(user)
                .status(0)
                .build();
        Attendance save = attendanceRepository.save(attendance);

        return save;
    }

    private Integer decideStatus(Session session) throws BaseException {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime start = session.getSessionStart();
        LocalDateTime end = session.getSessionEnd();
        Integer validTime = session.getGroupInfo().getAttendanceValidTime();
        Integer status = 0;
        if (now.isBefore(start))
            //지금시간 < 시작시간
            throw new BaseException(BaseResponseStatus.FAIL_ATTEND);
        else if (now.isAfter(start) && now.isBefore(start.plusMinutes(validTime)))
            //시작시간 < 지금시간 < 시작시간 + 유효시간
            status = 1; //정상출석
        else if (now.isAfter(start.plusMinutes(validTime)) && now.isBefore(end))
            //시작시간 + 유효시간 < 지금시간 < 끝 시간
            status = 2; //지각
        else if (now.isAfter(end))
            //끝 시간 < 지금 시간
            status = 3; //결석
        return status;
    }


    public Integer modifyAttendance(List<PatchAttendanceReq> patchAttendanceReqList) throws BaseException {
        Integer count = 0;
        for (PatchAttendanceReq patchAttendanceReq : patchAttendanceReqList) {
            count += attendanceRepository.modifyStatus(patchAttendanceReq.getAttendanceIdx(), patchAttendanceReq.getStatus());
        }
        return count;
    }

    public List<GetSessionAttendanceRes> getSessionAttendance(Long groupIdx) throws BaseException {
        if (!groupInfoRepository.existsById(groupIdx))
            throw new BaseException(BaseResponseStatus.INVALID_GROUP);
        List<User> userList = registerRepository.findUserByGroup(groupIdx);
        List<Session> sessionList = sessionRepository.getSessionAndAttendanceByGroupIdx(groupIdx);
        List<GetSessionAttendanceRes> getSessionAttendanceResList = new ArrayList<>();
        for (Session session : sessionList) {
            List<GetAttendanceInfo> getAttendanceInfoList = new ArrayList<>();
            for (Attendance attendance : session.getAttendances()) {
                GetAttendanceInfo getAttendanceInfo = GetAttendanceInfo.builder()
                        .attendanceIdx(attendance.getAttendanceIdx())
                        .nickname(attendance.getUser().getNickname())
                        .status(attendance.getStatus())
                        .build();
                getAttendanceInfoList.add(getAttendanceInfo);
            }
            GetSessionAttendanceRes getSessionAttendanceRes = GetSessionAttendanceRes.builder()
                    .sessionIdx(session.getSessionIdx())
                    .sessionNum(session.getSessionNum())
                    .getAttendanceInfos(getAttendanceInfoList)
                    .build();
            getSessionAttendanceResList.add(getSessionAttendanceRes);
        }
        return getSessionAttendanceResList;
    }

    //10분마다 동작
    //결석 체크하는 메서드
    @Scheduled(cron = "0 0/10 * * * *")
    public void schedulyUpdateAttendance() {
        System.out.println("regular attendances updating routine started");
        LocalDateTime now = LocalDateTime.now();
//        ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        List<Session> sessions = sessionRepository.
                getAllWithGroupAndAttendances(now, now.minusHours(9));
        //Start 이후, 끝난 or 끝날 시점은 지금으로부터 9시간 후 시각 이전
        if(sessions.isEmpty()){
            System.out.println("There is no session in progress");
            return;
        }
        for (Session session : sessions) {
            LocalDateTime end = session.getSessionEnd();
            List<Attendance> attendances = session.getAttendances();
            for (Attendance attendance : attendances) {
                Integer status = attendance.getStatus();
                if (status == 0) {
                        attendance.setStatus(3);
                }
            }
            sessionRepository.saveAll(sessions);
        }
    }
}
