package chess.domain.piece;

import chess.domain.movement.Movement;
import chess.domain.movement.direction.DownDirection;
import chess.domain.movement.direction.DownLeftDirection;
import chess.domain.movement.direction.DownRightDirection;
import chess.domain.movement.direction.KnightDirection;
import chess.domain.movement.direction.LeftDirection;
import chess.domain.movement.direction.RightDirection;
import chess.domain.movement.direction.UpDirection;
import chess.domain.movement.direction.UpLeftDirection;
import chess.domain.movement.direction.UpRightDirection;
import chess.domain.movement.policy.ColorPolicy;
import chess.domain.movement.policy.CombinationPolicy;
import chess.domain.movement.policy.EnemyExistPolicy;
import chess.domain.movement.policy.NoRestrictionPolicy;
import chess.domain.movement.policy.PawnFirstMovePolicy;
import chess.domain.piece.obstaclerule.DiagonalCaptureObstacleRule;
import chess.domain.piece.obstaclerule.NoObstacleRule;
import chess.domain.piece.obstaclerule.ObstacleRule;
import chess.domain.piece.obstaclerule.StraightCaptureObstacleRule;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;

public enum PieceType {
    KING(new StraightCaptureObstacleRule(),
            new Movement(new NoRestrictionPolicy(), new UpDirection(1)),
            new Movement(new NoRestrictionPolicy(), new DownDirection(1)),
            new Movement(new NoRestrictionPolicy(), new LeftDirection(1)),
            new Movement(new NoRestrictionPolicy(), new RightDirection(1)),
            new Movement(new NoRestrictionPolicy(), new UpLeftDirection(1)),
            new Movement(new NoRestrictionPolicy(), new UpRightDirection(1)),
            new Movement(new NoRestrictionPolicy(), new DownLeftDirection(1)),
            new Movement(new NoRestrictionPolicy(), new DownRightDirection(1))
    ),

    QUEEN(new StraightCaptureObstacleRule(),
            new Movement(new NoRestrictionPolicy(), new UpDirection(8)),
            new Movement(new NoRestrictionPolicy(), new DownDirection(8)),
            new Movement(new NoRestrictionPolicy(), new LeftDirection(8)),
            new Movement(new NoRestrictionPolicy(), new RightDirection(8)),
            new Movement(new NoRestrictionPolicy(), new UpLeftDirection(8)),
            new Movement(new NoRestrictionPolicy(), new UpRightDirection(8)),
            new Movement(new NoRestrictionPolicy(), new DownLeftDirection(8)),
            new Movement(new NoRestrictionPolicy(), new DownRightDirection(8))
    ),

    BISHOP(new StraightCaptureObstacleRule(),
            new Movement(new NoRestrictionPolicy(), new UpLeftDirection(8)),
            new Movement(new NoRestrictionPolicy(), new UpRightDirection(8)),
            new Movement(new NoRestrictionPolicy(), new DownLeftDirection(8)),
            new Movement(new NoRestrictionPolicy(), new DownRightDirection(8))
    ),

    ROOK(new StraightCaptureObstacleRule(),
            new Movement(new NoRestrictionPolicy(), new UpDirection(8)),
            new Movement(new NoRestrictionPolicy(), new DownDirection(8)),
            new Movement(new NoRestrictionPolicy(), new LeftDirection(8)),
            new Movement(new NoRestrictionPolicy(), new RightDirection(8))
    ),

    KNIGHT(new StraightCaptureObstacleRule(),
            new Movement(new NoRestrictionPolicy(), new KnightDirection())
    ),

    PAWN(new DiagonalCaptureObstacleRule(),
            new Movement(new CombinationPolicy(new ColorPolicy(Color.WHITE), new PawnFirstMovePolicy()),
                    new UpDirection(2)),
            new Movement(new CombinationPolicy(new ColorPolicy(Color.WHITE), new EnemyExistPolicy()),
                    new UpLeftDirection(1)),
            new Movement(new CombinationPolicy(new ColorPolicy(Color.WHITE), new EnemyExistPolicy()),
                    new UpRightDirection(1)),
            new Movement(new ColorPolicy(Color.WHITE), new UpDirection(1)),

            new Movement(new CombinationPolicy(new ColorPolicy(Color.BLACK), new PawnFirstMovePolicy()),
                    new DownDirection(2)),
            new Movement(new CombinationPolicy(new ColorPolicy(Color.BLACK), new EnemyExistPolicy()),
                    new DownLeftDirection(1)),
            new Movement(new CombinationPolicy(new ColorPolicy(Color.BLACK), new EnemyExistPolicy()),
                    new DownRightDirection(1)),
            new Movement(new ColorPolicy(Color.BLACK), new DownDirection(1))
    ),

    EMPTY(new NoObstacleRule()),
    ;

    private final ObstacleRule obstacleRule;
    private final List<Movement> movements;

    PieceType(final ObstacleRule obstacleRule, final Movement... movements) {
        this.obstacleRule = obstacleRule;
        this.movements = List.of(movements);
    }

    public List<Movement> getMovements() {
        return movements;
    }

    public List<Position> getObstacle(final Position source, final Position target, final Map<Position, Piece> pieces) {
        return obstacleRule.findObstacle(source, target, pieces);
    }
}
