package chess.domain.piece;

import chess.domain.Position;

public abstract class Piece {
    protected Position position;
    protected PieceMeta pieceMeta;

    public Piece(Position position, PieceMeta pieceMeta) {
        this.position = position;
        this.pieceMeta = pieceMeta;
    }

    public abstract void move(Position position);

    public boolean isSameType(PieceType pieceType) {
        return pieceMeta.isSameType(pieceType);
    }

    public boolean isBlack() {
        return pieceMeta.isBlack();
    }
}
