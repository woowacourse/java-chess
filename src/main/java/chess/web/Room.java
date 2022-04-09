package chess.web;

public class Room {
    private final long id;
    private final String name;
    private final boolean canJoin;
    private final String white;

    public Room(final long id, final String name, final boolean canJoin, final String white) {
        this.id = id;
        this.name = name;
        this.canJoin = canJoin;
        this.white = white;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isCanJoin() {
        return canJoin;
    }

    public String getWhite() {
        return white;
    }

    @Override
    public String toString() {
        return "Room{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", canJoin=" + canJoin +
            ", white='" + white + '\'' +
            '}';
    }
}
