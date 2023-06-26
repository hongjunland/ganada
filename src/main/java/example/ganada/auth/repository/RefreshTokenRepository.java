package example.ganada.auth.repository;

import example.ganada.auth.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByMember_MemberId(Long id);
    Optional<RefreshToken> findByRefreshTokenAndMemberMemberId(String token, Long memberId);
}
