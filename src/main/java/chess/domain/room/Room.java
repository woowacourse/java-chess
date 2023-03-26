package chess.domain.room;

public class Room {
    private final int id;
    private final String name;
    private final boolean finished;

    public Room(final int id, final String name, final boolean finished) {
        this.id = id;
        this.name = name;
        this.finished = finished;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isFinished() {
        return finished;
    }
}
