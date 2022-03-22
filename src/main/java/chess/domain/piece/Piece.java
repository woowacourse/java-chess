package chess.domain.piece;

import chess.domain.Position;

public abstract class Piece {

    private final Color color;
    private final PieceType pieceType;
    private final Position position;

    public Piece(Color color, PieceType pieceType, Position position) {
        this.color = color;
        this.pieceType = pieceType;
        this.position = position;
    }
}
