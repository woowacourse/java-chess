package chess.domain.piece;

import chess.domain.Team;
import chess.domain.piece.movement.Movement;
import chess.domain.piece.obstacleStrategy.ObstacleStrategy;
import chess.domain.position.Position;
import chess.domain.position.RelativePosition;

import java.util.List;

public abstract class NoneEmptyPiece extends Piece {

    protected final Movement movement;
    protected final ObstacleStrategy obstacleStrategy;

    public NoneEmptyPiece(PieceType pieceType, Team team, Movement movement, ObstacleStrategy obstacleStrategy) {
        super(pieceType, team);
        this.movement = movement;
        this.obstacleStrategy = obstacleStrategy;
    }

    protected void validateSameTeam(Piece target) {
        if (isSameTeam(target)) {
            throw new IllegalArgumentException("이동하고자 하는 자리에 같은 팀이 존재합니다.");
        }
    }

    protected void validateIllegalDirection(RelativePosition relativePosition) {
        if (!movement.isMobile(relativePosition)) {
            throw new IllegalArgumentException("해당 방향으로 이동할 수 없는 말입니다.");
        }
    }

    public List<Position> getObstacleCheckingPositions(Position from, Position to) {
        return obstacleStrategy.getObstacleCheckingPositions(from, to);
    }
}
