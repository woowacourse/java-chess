package chess.domain.piece;

import chess.domain.piece.movement.Movement;
import chess.domain.piece.obstacleStrategy.BlockedByObstacle;
import chess.domain.piece.obstacleStrategy.ObstacleStrategy;
import chess.domain.position.RelativePosition;

public class King extends NoneEmptyPiece {

    private King(final PieceType pieceType, final Team team, final Movement movement, final ObstacleStrategy obstacleStrategy) {
        super(pieceType, team, movement, obstacleStrategy);
    }

    public static King from(final Team team) {
        return new King(PieceType.KING, team, Movement.KING, new BlockedByObstacle());
    }

    @Override
    public boolean isMobile(final RelativePosition relativePosition, final Piece target) {
        validateSameTeam(target);
        validateIllegalDirection(relativePosition);
        return true;
    }

    @Override
    public double getScore() {
        return pieceType.getScore();
    }
}
