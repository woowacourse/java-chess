package chess.model;

import java.util.Map;

public class ChessBoard {
    private final Map<ChessPosition, MoveStrategy> board;

    public ChessBoard(Map<ChessPosition, MoveStrategy> board) {
        this.board = board;
    }
}
