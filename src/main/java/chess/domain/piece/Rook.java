package chess.domain.piece;

import chess.domain.movement.Movements;
import chess.domain.movement.MovementsFactory;
import chess.domain.movement.UnitMovement;

import java.util.Map;
import java.util.Set;

public class Rook extends Piece {
    private static final int MAX_MOVE_COUNT = 8;
    private static final Set<UnitMovement> COMMON_UNIT_MOVEMENTS = MovementsFactory.createStraight();
    private static final Movements COMMON_MOVEMENTS = new Movements(COMMON_UNIT_MOVEMENTS, COMMON_UNIT_MOVEMENTS);
    private static final Map<Color, Rook> ROOK_POOL = Map.of(
            Color.WHITE, new Rook(Color.WHITE, COMMON_MOVEMENTS),
            Color.BLACK, new Rook(Color.BLACK, COMMON_MOVEMENTS));

    private Rook(Color color, Movements movements) {
        super(color, movements);
    }

    public static Rook from(Color color) {
        return ROOK_POOL.get(color);
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
