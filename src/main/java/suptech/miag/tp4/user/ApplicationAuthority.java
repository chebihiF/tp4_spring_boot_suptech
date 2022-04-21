package suptech.miag.tp4.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "authority")
public class ApplicationAuthority {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @NonNull
    private String name ;
    @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ApplicationUser> users = new ArrayList<>();
}
