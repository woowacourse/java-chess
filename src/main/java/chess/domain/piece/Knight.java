package chess.domain.piece;

import chess.domain.movement.Movements;
import chess.domain.movement.MovementsFactory;
import chess.domain.movement.UnitMovement;

import java.util.Map;
import java.util.Set;

public class Knight extends Piece {
    private static final int MAX_MOVE_COUNT = 1;
    private static final Set<UnitMovement> COMMON_UNIT_MOVEMENTS = MovementsFactory.createKnightMovements();
    private static final Movements COMMON_MOVEMENTS = new Movements(COMMON_UNIT_MOVEMENTS, COMMON_UNIT_MOVEMENTS);
    private static final Map<Color, Knight> KNIGHT_POOL = Map.of(
            Color.WHITE, new Knight(Color.WHITE, COMMON_MOVEMENTS),
            Color.BLACK, new Knight(Color.BLACK, COMMON_MOVEMENTS));

    private Knight(Color color, Movements movements) {
        super(color, movements);
    }

    public static Knight from(Color color) {
        return KNIGHT_POOL.get(color);
    }

    @Override
    protected int maxPassMoveCount() {
        return MAX_MOVE_COUNT;
    }

    @Override
    protected int maxAttackMoveCount() {
        return MAX_MOVE_COUNT;
    }

    @Override
    public void move() {
    }
}
