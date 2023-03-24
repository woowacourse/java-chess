package chess.domain.piece;

import chess.domain.piece.movement.Movement;
import chess.domain.piece.obstacleStrategy.BlockedByObstacle;
import chess.domain.piece.obstacleStrategy.ObstacleStrategy;
import chess.domain.position.RelativePosition;

public final class Rook extends NoneEmptyPiece {

    private Rook(final PieceType pieceType, final Team team, final Movement movement, final ObstacleStrategy obstacleStrategy) {
        super(pieceType, team, movement, obstacleStrategy);
    }

    public static Rook from(final Team team) {
        return new Rook(PieceType.ROOK, team, Movement.ROOK, new BlockedByObstacle());
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
