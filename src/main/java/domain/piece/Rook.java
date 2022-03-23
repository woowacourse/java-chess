package domain.piece;

import static domain.utils.Direction.*;

import domain.position.Position;
import domain.position.XPosition;
import domain.position.YPosition;
import domain.utils.Direction;
import java.util.ArrayList;
import java.util.List;

public final class Rook extends Piece {

    private static final List<Direction> directions;
    private List<Position> positions;

    static {
        directions = new ArrayList<>();
        directions.add(EAST);
        directions.add(WEST);
        directions.add(SOUTH);
        directions.add(NORTH);
    }

    public Rook(final Player player) {
        super(player, Unit.Rook);
        initializePosition();
    }

    private void initializePosition() {
        positions = new ArrayList<>();
    }

    @Override
    public boolean availableMove(final Position source, final Position target) {
        calculateAvailablePositions(source);
        return positions.contains(target);
    }

    private void calculateAvailablePositions(final Position source) {
        initializePosition();
        for (Direction direction : directions) {
            calculateAvailablePosition(source, direction);
        }
    }

    private void calculateAvailablePosition(final Position source, final Direction direction) {
        int x = source.getX() + direction.getX();
        int y = source.getY() + direction.getY();

        while (checkOverRange(x, y)) {
            positions.add(new Position(XPosition.of(x), YPosition.of(y)));
            x += direction.getX();
            y += direction.getY();
        }
    }

    private boolean checkOverRange(final int x, final int y) {
        boolean xCondition = XPosition.A.getX() <= x && x <= XPosition.H.getX();
        boolean yCondition = YPosition.ONE.getY() <= y && y <= YPosition.EIGHT.getY();

        return xCondition && yCondition;
    }
}
