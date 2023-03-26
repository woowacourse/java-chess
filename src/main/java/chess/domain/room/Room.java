package chess.domain.room;

public class Room {
    private final int id;
    private final String name;
    private final boolean finished;
    private final int userId;

    public Room(final int id, final String name, final boolean finished, final int userId) {
        this.id = id;
        this.name = name;
        this.finished = finished;
        this.userId = userId;
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

    public int getUserId() {
        return userId;
    }

    public boolean isNotCreatedBy(final int id) {
        return userId != id;
    }
}
