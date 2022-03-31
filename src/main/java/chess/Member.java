package chess;

public class Member {
    private final String id;
    private final String name;
    private final Role role;

    public Member(final String id, final String name, final Role role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public Member(final String id, final String name) {
        this(id, name, new Role("basic"));
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
