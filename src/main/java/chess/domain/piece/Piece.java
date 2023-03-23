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

    public abstract void canMove(final int fileInterval, final int rankInterval, final boolean canAttack);

    public final boolean isSameType(PieceType pieceType) {
        return this.pieceType == pieceType;
    }

    public final Team getTeam() {
        return team;
    }

    public final String getName() {
        String name = pieceType.getName();
        if (team == BLACK) {
            return name.toUpperCase();
        }
        return name;
    }
}
