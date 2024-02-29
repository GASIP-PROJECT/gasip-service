package com.example.gasip.member.service;

import com.auth0.jwt.JWT;
import com.example.gasip.profboard.repository.ProfBoardRepository;
import com.example.gasip.global.security.JwtService;
import com.example.gasip.global.security.MemberDetails;
import com.example.gasip.member.dto.*;
import com.example.gasip.member.model.Member;
import com.example.gasip.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationProvider authenticationProvider;
    private final JwtService jwtService;
    private final ProfBoardRepository boardRepository;

    @Transactional
    public MemberSignUpResponse signup(MemberSignUpRequest memberSignUpRequest) {
        validateEmailDuplicated(memberSignUpRequest);
        Member member = memberSignUpRequest.toEntity(memberSignUpRequest);
        member.encodePassword(passwordEncoder);

        return MemberSignUpResponse.fromEntity(memberRepository.save(member));
    }

    @Transactional
    public MemberLogInResponse login(MemberLogInRequest memberLogInRequest) {
        Authentication authentication = authenticationProvider.authenticate(
            new UsernamePasswordAuthenticationToken(memberLogInRequest.getEmail(), memberLogInRequest.getPassword())
        );
        MemberDetails memberDetails = (MemberDetails) authentication.getPrincipal();
        Member member = memberRepository.getReferenceById(memberDetails.getId());
        String accessToken = jwtService.issue(member.getMemberId(), member.getEmail(), member.getRole());
        return MemberLogInResponse.fromEntity(member,accessToken);
    }

    @Transactional
    public MemberMyPageResponse getMyPage(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(
            (IllegalArgumentException::new)
        );
        return MemberMyPageResponse.fromEntity(member);
    }

    @Transactional
    public List<?> getBoards(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(
            (IllegalArgumentException::new)
        );
        return boardRepository.findContentsByMemberId(member.getMemberId());
    }

    @Transactional
    public Boolean isAuthenticated(String accessToken) {
        String token = accessToken.substring(7);
        Date expiresAt = JWT.decode(token).getExpiresAt();
        if (expiresAt.after(Date.from(Instant.now()))) {
            return true;
        } else {
            return false;
        }
    }

    private void validateEmailDuplicated(MemberSignUpRequest memberSignUpRequest) {
        memberRepository.findByEmail(memberSignUpRequest.getEmail()).ifPresent(member -> {
                throw new IllegalArgumentException();
            }
        );
    }
}
