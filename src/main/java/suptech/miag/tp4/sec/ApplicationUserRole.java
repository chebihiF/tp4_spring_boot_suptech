package suptech.miag.tp4.sec;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static suptech.miag.tp4.sec.ApplicationUserAuthority.*;

public enum ApplicationUserRole {
    ADMIN(Sets.newHashSet(PRODUCT_WRITE,PRODUCT_READ,PRODUCT_DELETE,PRODUCT_UPDATE)),
    MANAGER(Sets.newHashSet(PRODUCT_READ,PRODUCT_UPDATE)),
    USER(Sets.newHashSet(PRODUCT_READ));

    private final Set<ApplicationUserAuthority> authorities;

    ApplicationUserRole(Set<ApplicationUserAuthority> authorities) {
        this.authorities = authorities;
    }

    public List<SimpleGrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = this.authorities
                .stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return authorities;
    }
}
