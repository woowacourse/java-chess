package chess.domain.piece;

import chess.domain.Team;
import chess.domain.piece.movement.Movement;
import chess.domain.piece.obstacleStrategy.BlockedByObstacle;
import chess.domain.piece.obstacleStrategy.ObstacleStrategy;
import chess.domain.position.RelativePosition;

public class Queen extends NoneEmptyPiece {

    private Queen(PieceType pieceType, Team team, Movement movement, ObstacleStrategy obstacleStrategy) {
        super(pieceType, team, movement, obstacleStrategy);
    }

    public static Queen from(Team team) {
        return new Queen(PieceType.QUEEN, team, Movement.QUEEN, new BlockedByObstacle());
    }

    @Override
    public boolean isMobile(RelativePosition relativePosition, Piece target) {
        validateSameTeam(target);
        validateIllegalDirection(relativePosition);
        return true;
    }

}
