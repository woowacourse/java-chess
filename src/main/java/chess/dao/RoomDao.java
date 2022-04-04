package chess.dao;

public class RoomDao {
    private int id;
    private boolean status;

    public RoomDao(int id, boolean status) {
        this.id = id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public boolean isStatus() {
        return status;
    }
}
