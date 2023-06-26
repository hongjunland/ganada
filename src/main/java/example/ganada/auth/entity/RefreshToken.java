package example.ganada.auth.entity;

import example.ganada.common.BaseEntity;
import example.ganada.member.entity.Member;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "refresh_token")
public class RefreshToken extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "refresh_token_id")
    private Long refreshTokenId;

    @OneToOne
    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
    private Member member;

    @Column(name="refresh_token", nullable = false, unique = true)
    private String refreshToken;

    @Column(nullable = false, name = "expire_date")
    private Instant expireDate;
}
