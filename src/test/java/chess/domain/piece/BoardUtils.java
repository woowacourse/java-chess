package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Position;

import java.util.Map;

public class BoardUtils {
    
    private BoardUtils() {}
    
    public static Board put(Board board, Position position, Piece piece) {
        final Map<Position, Piece> testBoard = board.getBoard();
        testBoard.put(position, piece);
        return new Board(testBoard);
    }
}
