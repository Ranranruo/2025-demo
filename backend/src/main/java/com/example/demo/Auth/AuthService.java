package com.example.demo.Auth;

import com.example.demo.Auth.Exception.UsernameAlreadyExistsException;
import com.example.demo.Auth.DTO.SignUpRequestDTO;
import com.example.demo.Member.Member;
import com.example.demo.Member.MemberRepository;
import com.example.demo.MemberRoleBridge.MemberRoleBridge;
import com.example.demo.MemberRoleBridge.MemberRoleBridgeRepository;
import com.example.demo.Role.Role;
import com.example.demo.Role.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    MemberRepository memberRepository;
    RoleRepository roleRepository;
    MemberRoleBridgeRepository memberRoleBridgeRepository;

    public boolean signUp(SignUpRequestDTO signUpRequestDTO) {
        boolean isExists = memberRepository.existsMemberByUsername(signUpRequestDTO.getUsername());
        if(isExists) throw new UsernameAlreadyExistsException();
        Member member = Member.builder()
                .username(signUpRequestDTO.getUsername())
                .password(signUpRequestDTO.getPassword())
                .email(signUpRequestDTO.getEmail())
                .displayName(signUpRequestDTO.getDisplayName())
                .build();
        Role role = roleRepository.findByName("MEMBER");
//        memberRepository.save(member);

        Long memberId = member.getId();
        Long roleId = role.getId();

        MemberRoleBridge memberRoleBridge = MemberRoleBridge.builder()
                .memberId(memberId)
                .roleId(roleId)
                .build();

//        memberRoleBridgeRepository.save(memberRoleBridge);
        return true;
    }
}
