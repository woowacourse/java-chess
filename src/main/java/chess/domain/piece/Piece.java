package chess.domain.piece;

import static chess.domain.Team.BLACK;

import chess.domain.Team;

public abstract class Piece {
    private final Team team;
    private final PieceType type;

    protected Piece(final Team team, final PieceType type) {
        this.team = team;
        this.type = type;
    }

    public abstract void canMove(final int fileInterval, final int rankInterval, final boolean canAttack);

    public final boolean isSameType(PieceType pieceType) {
        return this.type == pieceType;
    }

    public final Team team() {
        return team;
    }

    public final String getName() {
        String name = type.getName();
        if (team == BLACK) {
            return name.toUpperCase();
        }
        return name;
    }

    public final double score() {
        return type.getScore();
    }

    public PieceType type() {
        return type;
    }

    @Override
    public String toString() {
        return String.join(" ", team.toString(), type.toString());
    }
}
