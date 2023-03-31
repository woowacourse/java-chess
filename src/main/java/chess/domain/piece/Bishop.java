package chess.domain.piece;

import chess.domain.piece.movement.Movement;
import chess.domain.piece.obstacleStrategy.BlockedByObstacle;
import chess.domain.piece.obstacleStrategy.ObstacleStrategy;
import chess.domain.position.RelativePosition;

public final class Bishop extends NoneEmptyPiece {

    private Bishop(final PieceType pieceType, final Team team, final Movement movement, final ObstacleStrategy obstacleStrategy) {
        super(pieceType, team, movement, obstacleStrategy);
    }

    public static Bishop from(final Team team) {
        return new Bishop(PieceType.BISHOP, team, Movement.BISHOP, new BlockedByObstacle());
    }

    @Override
    public boolean isMobile(RelativePosition relativePosition, Piece target) {
        validateSameTeam(target);
        validateIllegalDirection(relativePosition);
        return true;
    }

    @Override
    public double getScore() {
        return pieceType.getScore();
    }
}
