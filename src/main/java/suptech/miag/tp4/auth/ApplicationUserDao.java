package suptech.miag.tp4.auth;

import suptech.miag.tp4.user.ApplicationUser;

public interface ApplicationUserDao {
    ApplicationUserDetail getUserByUsername(String username);
}
