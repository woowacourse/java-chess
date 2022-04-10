package chess;

import java.util.ArrayList;
import java.util.List;

public class Room {

    private final int id;
    private final String title;
    private final List<Member> members;
    private final int boardId;

    public Room(String title, int boardId) {
        this(0, title, new ArrayList<>(), boardId);
    }

    public Room(String title, List<Member> members, int boardId) {
        this(0, title, members, boardId);
    }

    public Room(int id, String title, List<Member> members, int boardId) {
        this.id = id;
        this.title = title;
        this.members = members;
        this.boardId = boardId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<Member> getMembers() {
        return members;
    }

    public int getBoardId() {
        return boardId;
    }
}
