package com.example.demo.src.service;

import com.example.demo.src.entity.GroupInfo;
import com.example.demo.src.entity.Register;
import com.example.demo.src.repository.GroupInfoRepository;
import com.example.demo.src.repository.RegisterRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
public class GroupInfoService {
    private final GroupInfoRepository groupInfoRepository;
    private final RegisterRepository registerRepository;

    public GroupInfoService(GroupInfoRepository groupInfoRepository, RegisterRepository registerRepository) {
        this.groupInfoRepository = groupInfoRepository;
        this.registerRepository = registerRepository;
    }
    public Long createStudy(){
        return 1L;
    }

    public List<GroupInfo> loadStudies(Long userIdx) {
        List<Register> registers = registerRepository.findByUser_UserIdxAndStatusIs(userIdx, 0);
        List<GroupInfo> studies = new ArrayList<>();
        for (Register register : registers) {
            studies.add(register.getGroupInfo());
        }
        return studies;
    }
}
