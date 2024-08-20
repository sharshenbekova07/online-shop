package kg.booster.onlineshop.enums;

import org.springframework.security.core.GrantedAuthority;

public enum RoleStatus implements GrantedAuthority {
    USER,
    ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
