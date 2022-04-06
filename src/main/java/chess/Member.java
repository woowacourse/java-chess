package chess;

public class Member {

    private int id;
    private String name;
    private int boardId;

    public Member(String name, int boardId) {
        this(0, name, boardId);
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
