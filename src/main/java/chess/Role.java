package chess;

public class Role {
    private final String role;

    public Role(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Role{" +
            "role='" + role + '\'' +
            '}';
    }

    public String getRole() {
        return role;
    }
}
