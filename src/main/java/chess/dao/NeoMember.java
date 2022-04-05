package chess.dao;

public class NeoMember {

    private final int id;
    private final String name;
    private final int boardId;

    public NeoMember(int id, String name, int boardId) {
        this.id = id;
        this.name = name;
        this.boardId = boardId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBoardId() {
        return boardId;
    }
}
