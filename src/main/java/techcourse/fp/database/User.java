package techcourse.fp.database;

public class User {

    private final String id;
    private final String name;

    public User(final String id, final String name) {
        this.id = id;
        this.name = name;
    }

    public String userId() {
        return id;
    }

    public String userName() {
        return name;
    }

}
