package chess.domain.piece;

import chess.domain.Team;
import chess.domain.piece.movement.Movement;
import chess.domain.piece.obstacleStrategy.BlockedByObstacle;
import chess.domain.piece.obstacleStrategy.ObstacleStrategy;
import chess.domain.position.RelativePosition;

public class Pawn extends NoneEmptyPiece {

    private boolean hasMoved;

    private Pawn(PieceType pieceType, Team team, Movement movement, ObstacleStrategy obstacleStrategy, boolean hasMoved) {
        super(pieceType, team, movement, obstacleStrategy);
        this.hasMoved = hasMoved;
    }

    public static Pawn from(Team team) {
        return new Pawn(PieceType.PAWN, team, Movement.PAWN, new BlockedByObstacle(), false);
    }

    @Override
    public boolean isMobile(RelativePosition relativePosition, Piece target) {
        validateSameTeam(target);
        validateIllegalTwoBlocksMove(relativePosition);

        if (!hasMoved && isMoveTwoBlocks(relativePosition)) {
            relativePosition = relativePosition.toUnit();
        }
        relativePosition = inverseDirectionIfBlackPawn(relativePosition);

        validateIllegalDirection(relativePosition);
        validateIllegalDiagonalMove(relativePosition, target);
        hasMoved = true;
        return true;
    }

    private void validateIllegalTwoBlocksMove(RelativePosition relativePosition) {
        if (hasMoved && isMoveTwoBlocks(relativePosition)) {
            throw new IllegalArgumentException("폰은 처음 이동할 때만 앞으로 두 칸 갈 수 있습니다.");
        }
    }

    private void validateIllegalDiagonalMove(RelativePosition relativePosition, Piece target) {
        if (relativePosition.isDiagonal() && (target.isEmpty() || isSameTeam(target))) {
            throw new IllegalArgumentException("폰은 상대팀을 공격할 때만 대각선으로 이동 가능합니다.");
        }
    }

    private boolean isMoveTwoBlocks(RelativePosition relativePosition) {
        return relativePosition.equals(new RelativePosition(0, 2)) || relativePosition.equals(new RelativePosition(0, -2));
    }

    private RelativePosition inverseDirectionIfBlackPawn(RelativePosition relativePosition) {
        if (team.isBlack()) {
            return relativePosition.inverseByXAxis();
        }
        return relativePosition;
    }
}
