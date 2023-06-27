package example.ganada.auth.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRepository {
    private final RedisTemplate<String, String> redisTemplate;
    private final String PRE_FIX = "refreshToken:";

    public void saveRefreshToken(String memberId, String refreshToken) {
        redisTemplate.opsForValue().set(PRE_FIX + memberId, refreshToken);
    }

    public String getRefreshToken(String memberId) {
        return redisTemplate.opsForValue().get(PRE_FIX + memberId);
    }

    public void expire(String memberId, Long exp) {
        redisTemplate.expire(PRE_FIX + memberId, exp, TimeUnit.MILLISECONDS);
    }

}
