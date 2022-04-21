package suptech.miag.tp4.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;
import suptech.miag.tp4.user.ApplicationAuthority;
import suptech.miag.tp4.user.ApplicationAuthorityRepository;
import suptech.miag.tp4.user.ApplicationUser;
import suptech.miag.tp4.user.ApplicationUserRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class ApplicationUserRepositoryMySql implements ApplicationUserDao{

    private final ApplicationUserRepository userRepository;
    private final ApplicationAuthorityRepository authorityRepository;

    public ApplicationUserRepositoryMySql(ApplicationUserRepository userRepository, ApplicationAuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }

    @Override
    public ApplicationUserDetail getUserByUsername(String username) {

        ApplicationUser user = userRepository.findById(username).get();
        List<ApplicationAuthority> authorities = authorityRepository.findApplicationAuthoritiesByUsersContains(user);
        Set<GrantedAuthority> grantedAuthorities = authorities
                .stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toSet());

        return new ApplicationUserDetail(
                user.getPassword(),
                user.getUsername(),
                grantedAuthorities,
                !user.isAccountNonExpired(),
                !user.isAccountNonLocked(),
                !user.isCredentialsNonExpired(),
                !user.isEnabled()
        );
    }
}
