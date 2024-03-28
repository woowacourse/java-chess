package chess.domain.user;

import chess.domain.Name;

public class User {

    private final Long id;
    private final Name name;

    private User(final Long id, final Name name) {
        this.id = id;
        this.name = name;
    }

    public static User from(final String name) {
        return new User(null, new Name(name));
    }

    public static User of(final Long id, final String name) {
        return new User(id, new Name(name));
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name.getName();
    }
}
