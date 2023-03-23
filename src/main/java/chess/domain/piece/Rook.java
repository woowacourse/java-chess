package chess.domain.piece;

import chess.domain.Team;
import chess.domain.piece.movement.Movement;
import chess.domain.piece.obstacleStrategy.BlockedByObstacle;
import chess.domain.piece.obstacleStrategy.ObstacleStrategy;
import chess.domain.position.RelativePosition;

public class Rook extends NoneEmptyPiece {

    private Rook(PieceType pieceType, Team team, Movement movement, ObstacleStrategy obstacleStrategy) {
        super(pieceType, team, movement, obstacleStrategy);
    }

    public static Rook from(Team team) {
        return new Rook(PieceType.ROOK, team, Movement.ROOK, new BlockedByObstacle());
    }

    @Override
    public boolean isMobile(RelativePosition relativePosition, Piece target) {
        validateSameTeam(target);
        validateIllegalDirection(relativePosition);
        return true;
    }
}
