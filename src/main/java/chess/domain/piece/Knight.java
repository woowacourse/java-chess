package chess.domain.piece;

import chess.domain.piece.movement.Movement;
import chess.domain.piece.obstacleStrategy.ObstacleStrategy;
import chess.domain.piece.obstacleStrategy.SkipObstacle;
import chess.domain.position.RelativePosition;

public class Knight extends NoneEmptyPiece {

    private Knight(final PieceType pieceType, final Team team, final Movement movement, final ObstacleStrategy obstacleStrategy) {
        super(pieceType, team, movement, obstacleStrategy);
    }

    public static Knight from(final Team team) {
        return new Knight(PieceType.KNIGHT, team, Movement.KNIGHT, new SkipObstacle());
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
