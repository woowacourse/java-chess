package chess.domain.piece;

import chess.domain.movement.Movements;
import chess.domain.movement.MovementsFactory;
import chess.domain.movement.UnitMovement;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Queen extends Piece {
    private static final int MAX_MOVE_COUNT = 8;
    private static final Set<UnitMovement> COMMON_UNIT_MOVEMENTS =
            Stream.concat(MovementsFactory.createStraight().stream(), MovementsFactory.createDiagonal().stream())
                    .collect(Collectors.toSet());
    private static final Movements COMMON_MOVEMENTS = new Movements(COMMON_UNIT_MOVEMENTS, COMMON_UNIT_MOVEMENTS);
    private static final Map<Color, Queen> QUEEN_POOL = Map.of(
            Color.WHITE, new Queen(Color.WHITE, COMMON_MOVEMENTS),
            Color.BLACK, new Queen(Color.BLACK, COMMON_MOVEMENTS));

    private Queen(Color color, Movements movements) {
        super(color, movements);
    }

    public static Queen from(Color color) {
        return QUEEN_POOL.get(color);
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
