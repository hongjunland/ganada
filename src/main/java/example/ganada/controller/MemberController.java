package example.ganada.controller;

import example.ganada.dto.member.CreateMemberRequest;
import example.ganada.dto.member.UpdateMemberRequest;
import example.ganada.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getMemberById(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.findById(id));
    }

    @GetMapping
    public ResponseEntity<?> getMembers() {
        return ResponseEntity.ok(memberService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> createMember(@RequestBody CreateMemberRequest createMemberRequest) {
        return ResponseEntity.ok(memberService.createMember(createMemberRequest));
    }

    @PutMapping("/{memberId}")
    public ResponseEntity<?> updateMember(@PathVariable Long memberId, @RequestBody UpdateMemberRequest updateMemberRequest) {
        return ResponseEntity.ok(memberService.updateMember(memberId, updateMemberRequest));
    }

    @DeleteMapping("/{memberId}")
    public void deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
    }


}
