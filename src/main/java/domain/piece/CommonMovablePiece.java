package domain.piece;

import domain.position.Position;
import domain.position.XPosition;
import domain.position.YPosition;
import domain.utils.Direction;
import java.util.ArrayList;
import java.util.List;

public abstract class CommonMovablePiece extends Piece {

    private List<Position> positions;

    public CommonMovablePiece(Player player, PieceSymbol unit) {
        super(player, unit);
    }

    private void initializePosition() {
        positions = new ArrayList<>();
    }

    public boolean availableMove(Position source, Position target) {
        calculateAvailablePositions(source);
        return positions.contains(target);
    }

    private void calculateAvailablePositions(final Position source) {
        initializePosition();
        for (Direction direction : directions()) {
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
        return XPosition.checkRange(x) && YPosition.checkRange(y);
    }
}
