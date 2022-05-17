package suptech.miag.tp4.sec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import suptech.miag.tp4.jwt.JwtUsernameAndPasswordFilter;

import static suptech.miag.tp4.sec.ApplicationUserAuthority.*;
import static suptech.miag.tp4.sec.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
public class ApplicationConfigurerAdapter extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    public ApplicationConfigurerAdapter(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
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
                .addFilter(new JwtUsernameAndPasswordFilter(authenticationManager()));

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    /*
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
                .username("manager")
                .password(passwordEncoder.encode("1234"))
//                .roles(MANAGER.name())
                .authorities(MANAGER.getAuthorities())
                .build();

        UserDetails user = User
                .builder()
                .username("user")
                .password(passwordEncoder.encode("1234"))
//                .roles(MANAGER.name())
                .authorities(USER.getAuthorities())
                .build();

        return new InMemoryUserDetailsManager(admin,manager,user);
    }*/
}
