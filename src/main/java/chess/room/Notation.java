package chess.room;

import java.util.ArrayList;
import java.util.List;

public class Notation {
    private final List<Move> notation;

    public Notation() {
        this.notation = new ArrayList<>();
    }

    public void add(Move move) {
        notation.add(move);
    }

    public List<Move> getNotation() {
        return notation;
    }
}
