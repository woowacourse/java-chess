package domain.piece.unit;

import domain.piece.property.PieceSymbol;
import domain.piece.property.TeamColor;
import domain.position.Position;
import domain.position.XPosition;
import domain.position.YPosition;
import domain.utils.Direction;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Piece {

    private final TeamColor teamColor;
    private final PieceSymbol unit;

    private Map<Direction, List<Position>> directionalPositions;

    public Piece(final TeamColor teamColor, final PieceSymbol unit) {
        this.teamColor = teamColor;
        this.unit = unit;
    }

    protected void initializePosition() {
        directionalPositions = new HashMap<>();
    }

    protected boolean checkOverRange(final int x, final int y) {
        return XPosition.checkRange(x) && YPosition.checkRange(y);
    }

    public boolean checkSameTeamColor(final TeamColor teamColor) {
        return this.teamColor == teamColor;
    }

    public boolean availableMove(final Position source, final Position target) {
        calculateAvailablePositions(source);
        return containsPosition(target);
    }

    public void addDirectionalPosition(final Direction direction, final List<Position> positions) {
        directionalPositions.put(direction, positions);
    }

    public List<Position> calculateRoute(Position target) {
        return directions().stream()
                .map(direction -> directionalPositions.get(direction))
                .filter(positions -> positions.contains(target))
                .findFirst()
                .orElse(null);
    }

    protected boolean containsPosition(final Position position) {
        return directions().stream()
                .map(direction -> directionalPositions.get(direction))
                .anyMatch(positions -> positions.contains(position));
    }

    protected void calculateAvailablePositions(final Position source) {
        initializePosition();
        for (Direction direction : directions()) {
            calculateAvailablePosition(source, direction);
        }
    }

    protected abstract void calculateAvailablePosition(final Position source, final Direction direction);

    protected abstract List<Direction> directions();

    public String symbol() {
        return unit.symbol(teamColor);
    }

    @Override
    public String toString() {
        return "Piece{" +
                "teamColor=" + teamColor +
                ", unit=" + unit +
                '}';
    }
}
