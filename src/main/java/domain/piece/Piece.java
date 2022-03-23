package domain.piece;

import domain.position.Position;
import domain.position.XPosition;
import domain.position.YPosition;
import domain.utils.Direction;
import java.util.ArrayList;
import java.util.List;

public abstract class Piece {

    private List<Position> positions;
    private final Player player;
    private final PieceSymbol unit;

    public Piece(final Player player, final PieceSymbol unit) {
        this.player = player;
        this.unit = unit;
    }

    public boolean checkSamePlayer(Player player) {
        return this.player == player;
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

    protected abstract List<Direction> directions();

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

    public String symbol() {
        return unit.symbol(player);
    }

    @Override
    public String toString() {
        return "Piece{" +
                "player=" + player +
                ", unit=" + unit +
                '}';
    }
}
