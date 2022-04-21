package suptech.miag.tp4.user;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor @RequiredArgsConstructor
@Table(name="user")
public class ApplicationUser {
    @Id @Column(length = 30) @NonNull
    private String username;
    @NonNull
    private String password;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    @ManyToMany
    @JoinTable(
            name = "userAuthority",
            joinColumns = @JoinColumn(name = "id_auth"),
            inverseJoinColumns = @JoinColumn(name = "id_user")
    )
    private List<ApplicationAuthority> authorities = new ArrayList<>();
}
