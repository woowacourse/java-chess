package chess.domain.piece;

import chess.domain.movement.Movements;
import chess.domain.movement.UnitMovement;

import java.util.Map;
import java.util.Set;

public class Pawn extends Piece {
    private static final int MAX_NOT_MOVED_PASS_MOVE_COUNT = 2;
    private static final int MAX_MOVED_PASS_MOVE_COUNT = 1;
    private static final int MAX_ATTACK_MOVE_COUNT = 1;
    private static final Movements WHITE_MOVEMENTS = new Movements(
            Set.of(UnitMovement.UP),
            Set.of(UnitMovement.LEFT_UP, UnitMovement.RIGHT_UP));
    private static final Movements BLACK_MOVEMENTS = new Movements(
            Set.of(UnitMovement.DOWN),
            Set.of(UnitMovement.LEFT_DOWN, UnitMovement.RIGHT_DOWN));
    private static final Map<Color, Movements> MOVEMENTS_POOL = Map.of(
            Color.WHITE, WHITE_MOVEMENTS,
            Color.BLACK, BLACK_MOVEMENTS);

    private boolean isMoved;

    private Pawn(Color color, Movements movements) {
        super(color, movements);
        isMoved = false;
    }

    public static Pawn createOnStart(Color color) {
        return new Pawn(color, MOVEMENTS_POOL.get(color));
    }

    @Override
    protected int maxPassMoveCount() {
        if (isMoved) {
            return MAX_MOVED_PASS_MOVE_COUNT;
        }

        return MAX_NOT_MOVED_PASS_MOVE_COUNT;
    }

    @Override
    protected int maxAttackMoveCount() {
        return MAX_ATTACK_MOVE_COUNT;
    }

    @Override
    public void move() {
        isMoved = true;
    }
}
