package chess.model;

import chess.Member;
import java.util.ArrayList;
import java.util.List;

public class Board {

    private final int id;
    private final String title;
    private final List<Member> members;

    public Board(int id, String title, List<Member> members) {
        this.id = id;
        this.title = title;
        this.members = members;
    }

    public Board(String title) {
        this(0, title, new ArrayList<>());
    }

    public Board(String title, List<Member> members) {
        this(0, title, members);
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
}
