package chess.dao;

public class RoomDao {
    private String id;
    private String status;

    public RoomDao(String id, String status) {
        this.id = id;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public boolean isWhiteTurn() {
        return status.equals("White");
    }
}
