package chess.domain.piece;

import chess.domain.Team;
import chess.domain.piece.movement.Movement;
import chess.domain.piece.obstacleStrategy.ObstacleStrategy;
import chess.domain.piece.obstacleStrategy.SkipObstacle;
import chess.domain.position.RelativePosition;

public class Knight extends NoneEmptyPiece {

    private Knight(PieceType pieceType, Team team, Movement movement, ObstacleStrategy obstacleStrategy) {
        super(pieceType, team, movement, obstacleStrategy);
    }

    public static Knight from(Team team) {
        return new Knight(PieceType.KNIGHT, team, Movement.KNIGHT, new SkipObstacle());
    }

    @Override
    public boolean isMobile(RelativePosition relativePosition, Piece target) {
        validateSameTeam(target);
        validateIllegalDirection(relativePosition);
        return true;
    }

}
