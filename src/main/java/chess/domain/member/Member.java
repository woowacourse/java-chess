package chess.domain.member;

public class Member {
    private final String id;
    private final String name;
    private Role role;

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Member(String id, String name, Role role) {
        this(id, name);
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
}
