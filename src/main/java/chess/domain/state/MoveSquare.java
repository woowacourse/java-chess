package chess.domain.state;

import chess.domain.board.BoardSquare;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import util.NullChecker;

public class MoveSquare {

    private final Map<MoveOrder, BoardSquare> squares;

    public MoveSquare(String before, String after) {
        NullChecker.validateNotNull(before, after);
        Map<MoveOrder, BoardSquare> squares = new HashMap<>();
        squares.put(MoveOrder.before, BoardSquare.of(before));
        squares.put(MoveOrder.after, BoardSquare.of(after));
        this.squares = Collections.unmodifiableMap(squares);
    }

    public BoardSquare get(MoveOrder moveOrder) {
        return squares.get(moveOrder);
    }


}
