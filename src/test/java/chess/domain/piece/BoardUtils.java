package chess.domain.piece;

import java.util.Map;

import chess.domain.board.Board;
import chess.domain.position.Position;

public class BoardUtils {

    private BoardUtils() {}

    public static Board put(Board board, Position position, Piece piece) {
        final Map<Position, Piece> testBoard = board.getBoard();
        testBoard.put(position, piece);
        return new Board(testBoard);
    }
}
