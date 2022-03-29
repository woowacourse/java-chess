package chess;

public class Member {

    private final String id;
    private final String name;
    private Role role;

    public Member(final String id, final String name) {
        this.id = id;
        this.name = name;
    }

    public Member(final String id, final String name, final Role role) {
        this.id = id;
        this.name = name;
        this.role = role;;
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
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", role=" + role +
                '}';
    }
}
