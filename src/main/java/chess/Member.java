package chess;

public class Member {
    private final String id;
    private final String name;
    private final String role;


    public Member(String id, String name) {
        this(id, name, "");
    }

    public Member(String id, String name, String role) {
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

    public String getRole() {
        return role;
    }
}
