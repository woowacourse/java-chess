package chess.domain.piece;

import chess.domain.movement.Movements;
import chess.domain.movement.MovementsFactory;
import chess.domain.movement.UnitMovement;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class King extends Piece {
    private static final int MAX_MOVE_COUNT = 1;
    private static final Set<UnitMovement> COMMON_UNIT_MOVEMENTS =
            Stream.concat(MovementsFactory.createStraight().stream(), MovementsFactory.createDiagonal().stream())
                    .collect(Collectors.toSet());
    private static final Movements COMMON_MOVEMENTS = new Movements(COMMON_UNIT_MOVEMENTS, COMMON_UNIT_MOVEMENTS);
    private static final Map<Color, King> KING_POOL = Map.of(
            Color.WHITE, new King(Color.WHITE, COMMON_MOVEMENTS),
            Color.BLACK, new King(Color.BLACK, COMMON_MOVEMENTS));

    private King(Color color, Movements movements) {
        super(color, movements);
    }

    public static King from(Color color) {
        return KING_POOL.get(color);
    }

    @Override
    protected int maxPassMoveCount() {
        return MAX_MOVE_COUNT;
    }

    @Override
    protected int maxAttackMoveCount() {
        return MAX_MOVE_COUNT;
    }
}
