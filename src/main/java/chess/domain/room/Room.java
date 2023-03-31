package chess.domain.room;

import chess.domain.piece.Color;

public class Room {

    private long id;
    private final long userId;
    private final String name;
    private final Color winner;

    public Room(long userId, String name) {
        validate(name);
        this.userId = userId;
        this.name = name;
        this.winner = Color.NONE;
    }

    public Room(long id, long userId, String name, Color winner) {
        validate(name);
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.winner = winner;
    }

    private void validate(String name) {
        if (name.isEmpty() || name.length() > 5) {
            throw new IllegalArgumentException("게임방 이름은 5자 이하여야합니다.");
        }
    }

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public Color getWinner() {
        return winner;
    }
}
