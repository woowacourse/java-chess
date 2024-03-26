package domain.piece.piecerole;

import domain.game.Direction;
import domain.game.Movable;
import domain.position.Position;
import java.util.List;

public class WhitePawn extends Pawn {
    private WhitePawn(final List<Movable> movables) {
        super(movables);
    }

    public static WhitePawn from() {
        List<Movable> routes = List.of(
                new Movable(ORIGINAL_MAX_MOVEMENT, Direction.NORTH),
                new Movable(ORIGINAL_MAX_MOVEMENT, Direction.NORTH_EAST),
                new Movable(ORIGINAL_MAX_MOVEMENT, Direction.NORTH_WEST)
        );
        return new WhitePawn(routes);
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
                    new Movable(INITIAL_MAX_MOVEMENT, Direction.NORTH),
                    new Movable(ORIGINAL_MAX_MOVEMENT, Direction.NORTH_EAST),
                    new Movable(ORIGINAL_MAX_MOVEMENT, Direction.NORTH_WEST)
            );
        }
        return routes;
    }

    @Override
    public boolean isStartPosition(final Position source) {
        return source.isRank2();
    }
}
