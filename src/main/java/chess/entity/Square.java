package chess.entity;

public class Square {

    private long id;
    private long roomId;
    private final String position;
    private final String piece;

    public Square(long id, long roomId, String position, String piece) {
        this.id = id;
        this.roomId = roomId;
        this.position = position;
        this.piece = piece;
    }

    public Square(String position, String piece) {
        this.position = position;
        this.piece = piece;
    }

    public long getId() {
        return id;
    }

    public long getRoomId() {
        return roomId;
    }

    public String getPosition() {
        return position;
    }

    public String getPiece() {
        return piece;
    }
}
