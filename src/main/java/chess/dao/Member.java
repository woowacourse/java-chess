package chess.dao;

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
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
