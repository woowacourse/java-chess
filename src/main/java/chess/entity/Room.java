package chess.entity;

import java.util.ArrayList;
import java.util.List;

public class Room {

    private long id;
    private String name;
    private final String turn;
    private final List<Square> board = new ArrayList<>();

    public Room(long id, String turn, String name) {
        this.id = id;
        this.turn = turn;
        this.name = name;
    }

    public Room(String name) {
        this.name = name;
        this.turn = "empty";
    }

    public long getId() {
        return id;
    }

    public String getTurn() {
        return turn;
    }

    public String getName() {
        return name;
    }

    public List<Square> getBoard() {
        return board;
    }

    public void addAll(List<Square> board) {
        this.board.addAll(board);
    }
}
