package chess.domain.piece;

import static chess.domain.Team.BLACK;

import chess.domain.Team;

public abstract class Piece {
    private final Team team;
    private final PieceType pieceType;

    protected Piece(final Team team, final PieceType pieceType) {
        this.team = team;
        this.pieceType = pieceType;
    }

    public abstract void canMove(int fileInterval, int rankInterval, boolean canAttack);

    public Team getColor() {
        return team;
    }

    public String getPieceTypeName() {
        String name = pieceType.getName();
        if (team == BLACK) {
            return name.toUpperCase();
        }
        return name;
    }

    public PieceType getPieceType() {
        return pieceType;
    }
}
