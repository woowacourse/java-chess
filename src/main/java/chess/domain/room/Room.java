package chess.domain.room;

public class Room {
    private final int id;
    private final String name;

    public Room(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
