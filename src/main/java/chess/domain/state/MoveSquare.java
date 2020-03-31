package chess.domain.state;

import chess.domain.board.BoardSquare;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import util.NullChecker;

public class MoveSquare {

    private final Map<MoveOrder, BoardSquare> squares;

    public MoveSquare(String before, String after) {
        this(BoardSquare.of(before), BoardSquare.of(after));
    }

    public MoveSquare(BoardSquare boardSquareBefore, BoardSquare boardSquareAfter) {
        NullChecker.validateNotNull(boardSquareBefore, boardSquareAfter);

        Map<MoveOrder, BoardSquare> squares = new HashMap<>();
        squares.put(MoveOrder.BEFORE, boardSquareBefore);
        squares.put(MoveOrder.AFTER, boardSquareAfter);

        this.squares = Collections.unmodifiableMap(squares);
    }

    public BoardSquare get(MoveOrder moveOrder) {
        return squares.get(moveOrder);
    }
}
