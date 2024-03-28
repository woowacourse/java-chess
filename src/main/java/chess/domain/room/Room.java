package chess.domain.room;

import chess.domain.Name;

public class Room {
    private final Long id;
    private final long userId;
    private final Name name;

    public Room(final long userId, final String name) {
        this(null, userId, name);
    }

    public Room(final Long id, final long userId, final String name) {
        this.id = id;
        this.userId = userId;
        this.name = new Name(name);
    }

    public Long getRoomId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public String getName() {
        return name.getName();
    }
}
