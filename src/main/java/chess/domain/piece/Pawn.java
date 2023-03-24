package chess.domain.piece;

import chess.domain.piece.movement.Movement;
import chess.domain.piece.obstacleStrategy.BlockedByObstacle;
import chess.domain.piece.obstacleStrategy.ObstacleStrategy;
import chess.domain.position.RelativePosition;

public final class Pawn extends NoneEmptyPiece {

    private boolean hasMoved;

    private Pawn(final PieceType pieceType, final Team team, final Movement movement,
                 final ObstacleStrategy obstacleStrategy, final boolean hasMoved) {
        super(pieceType, team, movement, obstacleStrategy);
        this.hasMoved = hasMoved;
    }

    public static Pawn from(final Team team) {
        return new Pawn(PieceType.PAWN, team, Movement.PAWN, new BlockedByObstacle(), false);
    }

    @Override
    public boolean isMobile(final RelativePosition relativePosition, final Piece target) {
        validateSameTeam(target);
        validateIllegalTwoBlocksMove(relativePosition);

        RelativePosition optimizedRelativePosition = relativePosition;
        if (!hasMoved && isMoveTwoBlocks(relativePosition)) {
            optimizedRelativePosition = relativePosition.toUnit();
        }
        optimizedRelativePosition = inverseDirectionIfBlackPawn(optimizedRelativePosition);

        validateIllegalDirection(optimizedRelativePosition);
        validateIllegalDiagonalMove(optimizedRelativePosition, target);
        hasMoved = true;
        return true;
    }

    private void validateIllegalTwoBlocksMove(final RelativePosition relativePosition) {
        if (hasMoved && isMoveTwoBlocks(relativePosition)) {
            throw new IllegalArgumentException("폰은 처음 이동할 때만 앞으로 두 칸 갈 수 있습니다.");
        }
    }

    private void validateIllegalDiagonalMove(final RelativePosition relativePosition, final Piece target) {
        if (relativePosition.isDiagonal() && (target.isEmpty() || isSameTeam(target))) {
            throw new IllegalArgumentException("폰은 상대팀을 공격할 때만 대각선으로 이동 가능합니다.");
        }
    }

    private boolean isMoveTwoBlocks(final RelativePosition relativePosition) {
        return relativePosition.equals(new RelativePosition(0, 2)) || relativePosition.equals(new RelativePosition(0, -2));
    }

    private RelativePosition inverseDirectionIfBlackPawn(final RelativePosition relativePosition) {
        if (team.isBlack()) {
            return relativePosition.inverseByXAxis();
        }
        return relativePosition;
    }

    @Override
    public double getScore() {
        return pieceType.getScore();
    }
}
