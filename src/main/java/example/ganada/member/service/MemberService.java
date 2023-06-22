package example.ganada.member.service;

import example.ganada.member.dto.CreateMemberRequest;
import example.ganada.member.dto.UpdateMemberRequest;
import example.ganada.member.entity.Member;
import example.ganada.member.mapper.MemberMapper;
import example.ganada.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    public Member createMember(CreateMemberRequest createMemberRequest){
        return memberRepository.save(MemberMapper.INSTANCE.toEntity(createMemberRequest));
    }
    public Member findById(Long id){
        Member member = memberRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("해당 회원이 존재하지 않습니다."));
        return member;
    }
    public Member findByEmail(String email){
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("해당 회원이 존재하지 않습니다."));
        return member;
    }

    public Member updateMember(Long id, UpdateMemberRequest updateMemberRequest){
        Member member = findById(id);
        MemberMapper.INSTANCE.updateMemberFromDto(member, updateMemberRequest);
        return memberRepository.save(member);
    }
    public void deleteMember(Long id){
        findById(id);
        memberRepository.deleteById(id);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }
}
