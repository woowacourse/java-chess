package chess.domain.piece;

import static chess.domain.Team.BLACK;

import chess.domain.Team;
import chess.domain.movement.Movement;

public abstract class Piece {
    private final Team team;
    private final PieceType pieceType;

    protected Piece(final Team team, final PieceType pieceType) {
        this.team = team;
        this.pieceType = pieceType;
    }

    public void validateMovement(final int fileInterval, final int rankInterval) {
        Movement movement = Movement.of(fileInterval, rankInterval);
        if (!pieceType.getMovements().contains(movement)) {
            throw new IllegalArgumentException("말이 이동할 수 없는 규칙입니다.");
        }
    }

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
