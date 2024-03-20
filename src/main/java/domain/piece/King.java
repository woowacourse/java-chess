package domain.piece;

import static domain.piece.Movable.*;

import domain.position.Position;
import java.util.List;

public class King implements PieceRole{

    private final List<Movable> routes;

    public King() {
        routes = List.of(UP, DOWN, LEFT, RIGHT);
    }

    @Override
    public boolean canMove(Position sourcePosition, Position targetPosition) {
        return routes.stream()
                .anyMatch(movable -> movable.canMove(sourcePosition, targetPosition));

    }
}
