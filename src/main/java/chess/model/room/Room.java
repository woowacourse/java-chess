package chess.model.room;

public class Room {

    private final int id;
    private final String title;
    private final int boardId;

    public Room(String title, int boardId) {
        this(0, title, boardId);
    }

    public Room(int id, String title, int boardId) {
        this.id = id;
        this.title = title;
        this.boardId = boardId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getBoardId() {
        return boardId;
    }
}
