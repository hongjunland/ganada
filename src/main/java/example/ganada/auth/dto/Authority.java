package example.ganada.auth.dto;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Authority implements GrantedAuthority {
    private String authority;
    @Override
    public String getAuthority() {
        return authority;
    }
}
