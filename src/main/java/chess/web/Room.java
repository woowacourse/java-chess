package chess.web;

public class Room {
    private final long id;
    private final String name;
    private final int canJoin;
    private final String currentCamp;

    public Room(final long id, final String name, final int canJoin, final String white) {
        this.id = id;
        this.name = name;
        this.canJoin = canJoin;
        this.currentCamp = white;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCanJoin() {
        return canJoin;
    }

    public String getCurrentCamp() {
        return currentCamp;
    }

    @Override
    public String toString() {
        return "Room{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", canJoin=" + canJoin +
            ", white='" + currentCamp + '\'' +
            '}';
    }
}
