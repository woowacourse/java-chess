package refactorChess.domain.board;

import java.util.Map;
import refactorChess.domain.piece.Piece;

public class ChessBoard {

    private final Map<Position, Piece> board;

    public ChessBoard(Map<Position, Piece> pieces) {
        this.board = pieces;
    }

    public static ChessBoard create() {
        return new ChessBoard(ChessBoardFactory.initChessBoard());
    }

    public Map<Position, Piece> getBoard() {
        return Map.copyOf(board);
    }
}
