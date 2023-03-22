package chess.domain.piece;

import chess.domain.board.Position;

public abstract class Piece {

    protected final Team team;
    protected final PieceType pieceType;

    protected Piece(final Team team, final PieceType pieceType) {
        this.team = team;
        this.pieceType = pieceType;
    }

    public abstract boolean isMovable(final Position from, final Position to);

    public boolean isEmpty() {
        return false;
    }

    public Team getTeam() {
        return this.team;
    }

    public PieceType getType() {
        return this.pieceType;
    }
}
