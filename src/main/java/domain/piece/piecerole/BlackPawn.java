package domain.piece.piecerole;

import domain.game.Direction;
import domain.game.Movable;
import domain.position.Position;
import java.util.List;

public class BlackPawn extends Pawn {
    private BlackPawn(final List<Movable> movables) {
        super(movables);
    }

    public static BlackPawn from() {
        List<Movable> routes = List.of(
                new Movable(ORIGINAL_MAX_MOVEMENT, Direction.SOUTH),
                new Movable(ORIGINAL_MAX_MOVEMENT, Direction.SOUTH_EAST),
                new Movable(ORIGINAL_MAX_MOVEMENT, Direction.SOUTH_WEST)
        );
        return new BlackPawn(routes);
    }

    @Override
    protected void validateCorrectRouteForPiece(final Position source,
                                                final Position target) {
        List<Movable> movables = generateCurrentMovable(source);
        boolean cannotMove = movables.stream()
                .noneMatch(movable -> movable.canMove(source, target));
        if (cannotMove) {
            throw new IllegalArgumentException("[ERROR]이동할 수 없는 경로입니다. 다시 입력해주세요.");
        }
    }

    private List<Movable> generateCurrentMovable(final Position source) {
        if (isStartPosition(source)) {
            return List.of(
                    new Movable(INITIAL_MAX_MOVEMENT, Direction.SOUTH),
                    new Movable(ORIGINAL_MAX_MOVEMENT, Direction.SOUTH_WEST),
                    new Movable(ORIGINAL_MAX_MOVEMENT, Direction.SOUTH_EAST)
            );
        }
        return routes;
    }

    @Override
    public boolean isStartPosition(final Position source) {
        return source.isRank7();
    }
}
