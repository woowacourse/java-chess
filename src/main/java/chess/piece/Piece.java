package chess.piece;

import chess.board.Position;

public abstract class Piece {

    protected final Team team;
    private final PieceType type;

    protected Piece(final Team team, final PieceType type) {
        this.team = team;
        this.type = type;
    }

    public boolean isPawn() {
        return false;
    }

    public boolean isKing() {
        return false;
    }

    public boolean isQueen() {
        return false;
    }

    public boolean isRook() {
        return false;
    }

    public boolean isKnight() {
        return false;
    }

    public boolean isBishop() {
        return false;
    }

    public boolean isEmpty() {
        return false;
    }

    public boolean isWhite() {
        return team == Team.WHITE;
    }

    public boolean isBlack() {
        return team == Team.BLACK;
    }

    public abstract boolean isMovable(final Position from, final Position to, final Piece piece);

    public Team getTeam() {
        return team;
    }

    public PieceType getType() {
        return type;
    }
}
