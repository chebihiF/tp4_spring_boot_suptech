package suptech.miag.tp4.sec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static suptech.miag.tp4.sec.ApplicationUserAuthority.*;
import static suptech.miag.tp4.sec.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
public class ApplicationConfigurerAdapter extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    public ApplicationConfigurerAdapter(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/","/index.html").permitAll()
                .antMatchers("/api/v1/**").hasAnyRole(ADMIN.name(),MANAGER.name())
                .antMatchers(HttpMethod.GET,"/api/v1/products/**")
                .hasAuthority(PRODUCT_READ.getAuthority())
                .antMatchers(HttpMethod.POST,"/api/v1/products/**")
                .hasAuthority(PRODUCT_WRITE.getAuthority())
                .antMatchers(HttpMethod.DELETE,"/api/v1/products/**")
                .hasAuthority(PRODUCT_DELETE.getAuthority())
                .antMatchers(HttpMethod.PUT,"/api/v1/products/**")
                .hasAuthority(PRODUCT_UPDATE.getAuthority())
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    public UserDetailsService userDetailsServiceBean() throws Exception {

        UserDetails admin = User
                .builder()
                .username("Admin")
                .password(passwordEncoder.encode("Admin"))
//                .roles(ADMIN.name())
                .authorities(ADMIN.getAuthorities())
                .build();

        UserDetails manager = User
                .builder()
                .username("user")
                .password(passwordEncoder.encode("1234"))
//                .roles(MANAGER.name())
                .authorities(MANAGER.getAuthorities())
                .build();

        return new InMemoryUserDetailsManager(admin,manager);
    }
}
