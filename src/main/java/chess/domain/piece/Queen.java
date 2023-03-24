package chess.domain.piece;

import chess.domain.piece.movement.Movement;
import chess.domain.piece.obstacleStrategy.BlockedByObstacle;
import chess.domain.piece.obstacleStrategy.ObstacleStrategy;
import chess.domain.position.RelativePosition;

public class Queen extends NoneEmptyPiece {

    private Queen(final PieceType pieceType, final Team team, final Movement movement, final ObstacleStrategy obstacleStrategy) {
        super(pieceType, team, movement, obstacleStrategy);
    }

    public static Queen from(final Team team) {
        return new Queen(PieceType.QUEEN, team, Movement.QUEEN, new BlockedByObstacle());
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
