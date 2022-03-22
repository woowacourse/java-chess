package chess.domain.piece;

import chess.domain.Color;
import chess.domain.PieceType;

public abstract class Piece {

    private final Color color;
    private final PieceType pieceType;

    public Piece(Color color, PieceType pieceType) {
        this.color = color;
        this.pieceType = pieceType;
    }
}
