package chess.domain.piece;

import chess.domain.Team;
import chess.domain.movement.Movement;

public abstract class Piece {
    private final Team team;
    private final PieceType pieceType;

    protected Piece(final Team team, final PieceType pieceType) {
        this.team = team;
        this.pieceType = pieceType;
    }

    public void validateMovement(int fileInterval, int rankInterval) {
        Movement movement = Movement.of(fileInterval, rankInterval);
        if (!pieceType.getMovements().contains(movement)) {
            throw new IllegalArgumentException("말이 이동할 수 없는 규칙입니다.");
        }
    }

    public Team getColor() {
        return team;
    }

    public String getPieceTypeName() {
        return pieceType.getName();
    }

    public PieceType getPieceType() {
        return pieceType;
    }
}
