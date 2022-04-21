package suptech.miag.tp4.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationAuthorityRepository extends JpaRepository<ApplicationAuthority,Long> {
    // select * from authority where id_authority in (select id_authority from user_authority where id_user = user.id)
    List<ApplicationAuthority> findApplicationAuthoritiesByUsersContains(ApplicationUser user);
}
