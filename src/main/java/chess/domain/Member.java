package chess.domain;

import chess.domain.piece.Role;
import java.util.Objects;

public class Member {
    private final String id;
    private final String name;
    private final Role role;

    public Member(String id, String name) {
        this(id, name, null);
    }

    public Member(String id, String name, Role role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Member member = (Member) o;
        return id.equals(member.id) && name.equals(member.name) && Objects.equals(role, member.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, role);
    }
}
