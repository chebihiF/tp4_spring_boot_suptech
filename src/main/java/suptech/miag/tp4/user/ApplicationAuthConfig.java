package suptech.miag.tp4.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class ApplicationAuthConfig {

    private final ApplicationUserRepository userRepository;
    private final ApplicationAuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    public ApplicationAuthConfig(ApplicationUserRepository userRepository, ApplicationAuthorityRepository authorityRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //@Bean
    CommandLineRunner initUserAuth(){
        return args -> {
            // users
            ApplicationUser admin = userRepository.save
                    (new ApplicationUser("admin",passwordEncoder.encode("admin")));
            ApplicationUser manager = userRepository.save
                    (new ApplicationUser("manager",passwordEncoder.encode("1234")));
            ApplicationUser user = userRepository.save
                    (new ApplicationUser("user",passwordEncoder.encode("1234")));

            //Authorities
            ApplicationAuthority product_write = authorityRepository.save
                    (new ApplicationAuthority("product:write"));
            ApplicationAuthority product_read = authorityRepository.save
                    (new ApplicationAuthority("product:read"));
            ApplicationAuthority product_update = authorityRepository.save
                    (new ApplicationAuthority("product:update"));
            ApplicationAuthority product_delete = authorityRepository.save
                    (new ApplicationAuthority("product:delete"));
            //Role
            ApplicationAuthority role_admin = authorityRepository.save
                    (new ApplicationAuthority("ROLE_ADMIN"));
            ApplicationAuthority role_manager = authorityRepository.save
                    (new ApplicationAuthority("ROLE_MANAGER"));
            ApplicationAuthority role_user = authorityRepository.save
                    (new ApplicationAuthority("ROLE_USER"));

            admin.getAuthorities().addAll(List.of(product_write,product_read,product_delete,product_update,role_admin));
            manager.getAuthorities().addAll(List.of(product_write,product_read,product_update,role_manager));
            user.getAuthorities().addAll(List.of(product_read,role_user));
            userRepository.save(admin);
            userRepository.save(manager);
            userRepository.save(user);


            /*product_write.getUsers().addAll(List.of(admin,manager));
            authorityRepository.save(product_write);

            role_admin.getUsers().add(admin);
            authorityRepository.save(role_admin);*/
        };
    }
}
