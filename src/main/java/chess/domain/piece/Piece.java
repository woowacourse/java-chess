package chess.domain.piece;

import chess.domain.*;

public abstract class Piece {
    protected final Aliance aliance;
    protected final PieceValue pieceValue;

    public Piece(Aliance aliance, PieceValue pieceValue) {
        this.aliance = aliance;
        this.pieceValue = pieceValue;
    }

    public abstract void checkPossibleMove(Board board, Position startPosition, Navigator navigator);

    public boolean isDifferentTeam(Aliance thisTurn) {
        return aliance != thisTurn;
    }

    public Aliance getAliance() {
        return aliance;
    }

    public PieceValue getPieceValue() {
        return pieceValue;
    }
}
