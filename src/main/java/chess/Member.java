package chess;

public class Member {

    private final int id;
    private final String name;
    private final int boardId;

    public Member(String name) {
        this(0, name, 0);
    }

    public Member(int id, String name, int boardId) {
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
