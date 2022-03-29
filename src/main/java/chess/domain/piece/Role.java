package chess.domain.piece;

import java.util.Objects;

public class Role {
    private final String Role;

    public Role(String role) {
        Role = role;
    }

    public String getRole() {
        return Role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Role role = (Role) o;
        return Objects.equals(Role, role.Role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Role);
    }
}
