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
import chess.domain.movement.policy.FirstMovePolicy;
import chess.domain.movement.policy.NoRestrictionPolicy;
import java.util.List;

public enum PieceType {
    KING(
            new Movement(new NoRestrictionPolicy(), new UpDirection(1)),
            new Movement(new NoRestrictionPolicy(), new DownDirection(1)),
            new Movement(new NoRestrictionPolicy(), new LeftDirection(1)),
            new Movement(new NoRestrictionPolicy(), new RightDirection(1)),
            new Movement(new NoRestrictionPolicy(), new UpLeftDirection(1)),
            new Movement(new NoRestrictionPolicy(), new UpRightDirection(1)),
            new Movement(new NoRestrictionPolicy(), new DownLeftDirection(1)),
            new Movement(new NoRestrictionPolicy(), new DownRightDirection(1))
    ),
    QUEEN(
            new Movement(new NoRestrictionPolicy(), new UpDirection(8)),
            new Movement(new NoRestrictionPolicy(), new DownDirection(8)),
            new Movement(new NoRestrictionPolicy(), new LeftDirection(8)),
            new Movement(new NoRestrictionPolicy(), new RightDirection(8)),
            new Movement(new NoRestrictionPolicy(), new UpLeftDirection(8)),
            new Movement(new NoRestrictionPolicy(), new UpRightDirection(8)),
            new Movement(new NoRestrictionPolicy(), new DownLeftDirection(8)),
            new Movement(new NoRestrictionPolicy(), new DownRightDirection(8))
    ),
    BISHOP(
            new Movement(new NoRestrictionPolicy(), new UpLeftDirection(8)),
            new Movement(new NoRestrictionPolicy(), new UpRightDirection(8)),
            new Movement(new NoRestrictionPolicy(), new DownLeftDirection(8)),
            new Movement(new NoRestrictionPolicy(), new DownRightDirection(8))
    ),
    ROOK(
            new Movement(new NoRestrictionPolicy(), new UpDirection(8)),
            new Movement(new NoRestrictionPolicy(), new DownDirection(8)),
            new Movement(new NoRestrictionPolicy(), new LeftDirection(8)),
            new Movement(new NoRestrictionPolicy(), new RightDirection(8))
    ),
    KNIGHT(
            new Movement(new NoRestrictionPolicy(), new KnightDirection())
    ),

    PAWN(
            new Movement(new CombinationPolicy(new ColorPolicy(Color.WHITE), new FirstMovePolicy()),
                    new UpDirection(2)),
            new Movement(new CombinationPolicy(new ColorPolicy(Color.WHITE), new EnemyExistPolicy()),
                    new UpLeftDirection(1)),
            new Movement(new CombinationPolicy(new ColorPolicy(Color.WHITE), new EnemyExistPolicy()),
                    new UpRightDirection(1)),
            new Movement(new ColorPolicy(Color.WHITE), new UpDirection(1)),

            new Movement(new CombinationPolicy(new ColorPolicy(Color.BLACK), new FirstMovePolicy()),
                    new DownDirection(2)),
            new Movement(new CombinationPolicy(new ColorPolicy(Color.BLACK), new EnemyExistPolicy()),
                    new DownLeftDirection(1)),
            new Movement(new CombinationPolicy(new ColorPolicy(Color.BLACK), new EnemyExistPolicy()),
                    new DownRightDirection(1)),
            new Movement(new ColorPolicy(Color.BLACK), new DownDirection(1))
    ),
    EMPTY(),
    ;

    private final List<Movement> movements;

    PieceType(final Movement... movements) {
        this.movements = List.of(movements);
    }

    public List<Movement> getMovements() {
        return movements;
    }
}
