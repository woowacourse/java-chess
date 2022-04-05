package chess.entity;

import java.util.ArrayList;
import java.util.List;

public class Room {

    private long id;
    private final String turn;
    private final List<Square> board = new ArrayList<>();

    public Room(long id, String turn) {
        this.id = id;
        this.turn = turn;
    }

    public Room() {
        this.turn = "white";
    }

    public long getId() {
        return id;
    }

    public String getTurn() {
        return turn;
    }

    public List<Square> getBoard() {
        return board;
    }

    public void addAll(List<Square> board) {
        this.board.addAll(board);
    }
}
