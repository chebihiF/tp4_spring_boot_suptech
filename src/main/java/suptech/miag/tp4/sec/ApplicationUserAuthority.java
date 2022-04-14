package suptech.miag.tp4.sec;

public enum ApplicationUserAuthority {
    PRODUCT_READ("product:read"),
    PRODUCT_WRITE("product:write"),
    PRODUCT_DELETE("product:delete"),
    PRODUCT_UPDATE("product:update");

    private final String authority;

    public String getAuthority() {
        return authority;
    }

    ApplicationUserAuthority(String authority) {
        this.authority = authority;
    }
}
