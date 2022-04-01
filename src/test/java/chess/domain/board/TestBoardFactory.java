package chess.domain.board;

import chess.domain.piece.Piece;
import java.util.Map;

public class TestBoardFactory {
    private final Map<Position, Piece> testBoard;

    public TestBoardFactory(Map<Position, Piece> testBoard) {
        this.testBoard = testBoard;
    }

    public Board createBoard() {
        return new Board(testBoard);
    }
}
