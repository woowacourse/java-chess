package chess.domain.user;

import chess.domain.Name;

public class User {

    private final Long id;
    private final Name name;

    public User(final String name) {
        this(null, name);
    }

    public User(final Long id, final String name) {
        this.id = id;
        this.name = new Name(name);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name.getName();
    }
}
