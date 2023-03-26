package chess.domain.user;

public class User {

    private long id;
    private final String name;

    public User(String name) {
        validate(name);
        this.name = name;
    }

    public User(long id, String name) {
        this(name);
        this.id = id;
    }

    private void validate(String name) {
        if (name.isEmpty() || name.length() > 5) {
            throw new IllegalArgumentException("사용자 이름은 5자 이하여야합니다.");
        }
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
