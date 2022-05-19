package suptech.miag.tp4.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import suptech.miag.tp4.user.ApplicationAuthority;
import suptech.miag.tp4.user.ApplicationAuthorityRepository;
import suptech.miag.tp4.user.ApplicationUser;
import suptech.miag.tp4.user.ApplicationUserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class AuthController {

    private final ApplicationAuthorityRepository authorityRepository;
    private final ApplicationUserRepository userRepository;

    public AuthController(ApplicationAuthorityRepository authorityRepository, ApplicationUserRepository userRepository) {
        this.authorityRepository = authorityRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/refreshToken")
    public void refresh_token(HttpServletRequest request, HttpServletResponse response){
        String token = request.getHeader("Authorization");
        if(token != null && token.startsWith("Bearer ")){
            token = token.replace("Bearer ","");
            // verification du refresh token
            String secretKey = "AOILHDLKJH@@1SDLKZ&&-LKDJ24456??!:!skljdml";
            try {
                Jws<Claims> claimsJws = Jwts
                        .parserBuilder()
                        .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                        .build().parseClaimsJws(token);
                Claims body = claimsJws.getBody();
                String username = body.getSubject();

                ApplicationUser user = userRepository.findById(username).get();
                List<ApplicationAuthority> user_authorities = authorityRepository.findApplicationAuthoritiesByUsersContains(user);
                List<Map<String,String>> authorities = new ArrayList<>();
                for(ApplicationAuthority authority : user_authorities) {
                    Map<String,String> map = new HashMap<String,String>();
                    map.put("authority", authority.getName());
                    authorities.add(map);
                }

                String access_token = Jwts
                        .builder()
                        .setSubject(username)
                        .claim("authorities", authorities)
                        .setIssuedAt(new Date())
                        .setExpiration(new Date(System.currentTimeMillis() + 30 * 1000))
                        .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                        .compact();

                response.setHeader("Access_Token",access_token);
            }catch (JwtException e){
                throw new IllegalStateException("Refresh token "+token+" cannot be trusted");
            }
            // creation new access_token
        }
    }

}
