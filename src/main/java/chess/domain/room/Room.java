package chess.domain.room;

import chess.domain.Name;

public class Room {
    private final Long id;
    private final long userId;
    private final Name name;

    public Room(final Long id, final long userId, final Name name) {
        this.id = id;
        this.userId = userId;
        this.name = name;
    }

    public static Room of(final long userId, final String name) {
        return new Room(null, userId, new Name(name));
    }

    public static Room of(final Long id, final long userId, final String name) {
        return new Room(id, userId, new Name(name));
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
