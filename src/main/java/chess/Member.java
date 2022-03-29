package chess;

public class Member {

    private String id;
    private String name;
    private Role role;

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
}
