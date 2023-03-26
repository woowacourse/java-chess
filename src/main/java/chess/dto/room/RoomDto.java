package chess.dto.room;

public class RoomDto {
    private final int id;
    private final String name;
    private final boolean finished;

    public RoomDto(final int id, final String name, final boolean finished) {
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
