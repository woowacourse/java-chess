package chess.domain.piece;

import chess.domain.Team;
import chess.domain.movement.Movement;
import chess.domain.piece.obstacleStrategy.BlockedByObstacle;
import chess.domain.piece.obstacleStrategy.ObstacleStrategy;
import chess.domain.position.RelativePosition;

public class King extends NoneEmptyPiece {

    private King(PieceType pieceType, Team team, Movement movement, ObstacleStrategy obstacleStrategy) {
        super(pieceType, team, movement, obstacleStrategy);
    }

    public static King from(Team team) {
        return new King(PieceType.KING, team, Movement.KING, new BlockedByObstacle());
    }

    @Override
    public boolean isMobile(RelativePosition relativePosition, Piece target) {
        validateSameTeam(target);
        validateIllegalDirection(relativePosition);
        return true;
    }
}
