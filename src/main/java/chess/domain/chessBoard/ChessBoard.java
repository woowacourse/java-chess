package chess.domain.chessBoard;

import chess.domain.chessPiece.Piece;
import chess.domain.position.Position;

import java.util.Map;

public class ChessBoard {
    private final Map<Position, Piece> pieces;

    public ChessBoard() {
        this.pieces = ChessBoardFactory.create();
    }
}
