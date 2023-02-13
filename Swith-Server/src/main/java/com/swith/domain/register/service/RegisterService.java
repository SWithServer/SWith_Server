package com.swith.domain.register.service;


import com.swith.domain.register.entity.Register;
import com.swith.domain.register.repository.RegisterRepository;
import com.swith.global.error.ErrorCode;
import com.swith.global.error.exception.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegisterService {

    private final RegisterRepository registerRepository;

    @Autowired
    public RegisterService(RegisterRepository registerRepository) {
        this.registerRepository = registerRepository;
    }

    @Transactional
    public Register save(Register register){
        return registerRepository.save(register);

    }

    public Register getOneActiveUser(Long groupIdx, Long userIdx){
        return registerRepository.findRegisterByGroupIdxandUserIdx(groupIdx,userIdx,0).orElseThrow(
                () -> new BaseException(ErrorCode.NO_REGISTER_MEMBER)
        );
    }

    public Integer getMemberCount(Long groupIdx){
        return registerRepository.findActiveUser(groupIdx);
    }

}
