package example.ganada.service;

import example.ganada.dto.member.CreateMemberRequest;
import example.ganada.dto.member.UpdateMemberRequest;
import example.ganada.entity.Member;
import example.ganada.mapper.MemberMapper;
import example.ganada.repository.MemberRepository;
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
        Member member = findById(id);
        memberRepository.deleteById(id);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }
}
