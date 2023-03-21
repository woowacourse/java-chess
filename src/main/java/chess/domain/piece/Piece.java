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

    public final void validateMovement(final int fileInterval, final int rankInterval, final Piece target) {
        if (!isValidMove(fileInterval, rankInterval, target) || !isValidTeam(target)) {
            throw new IllegalArgumentException("같은 팀 말은 공격할 수 없습니다.");
        }
    }

    public abstract boolean isValidMove(final int fileInterval, final int rankInterval, final Piece target);

    public abstract boolean isValidTeam(final Piece target);

    public abstract boolean isInitialPawn();

    public Team getTeam() {
        return team;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public String getPieceTypeName() {
        String name = pieceType.getName();
        if (team == BLACK) {
            return name.toUpperCase();
        }
        return name;
    }
}
