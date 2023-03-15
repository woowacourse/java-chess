package chess.domain.board;

import chess.domain.pieces.Piece;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> chessBoard;

    public Board(final Map<Position, Piece> chessBoard) {
        this.chessBoard = chessBoard;
    }

    public Map<Position, Piece> getChessBoard() {
        return chessBoard;
    }
}
