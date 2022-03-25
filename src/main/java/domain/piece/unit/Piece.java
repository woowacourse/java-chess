package domain.piece.unit;

import domain.piece.property.PieceSymbol;
import domain.piece.property.Team;
import domain.position.Position;
import domain.position.XPosition;
import domain.position.YPosition;
import domain.utils.Direction;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Piece {

    private final Team Team;
    private final PieceSymbol unit;

    protected Map<Direction, List<Position>> directionalPositions;

    public Piece(final Team Team, final PieceSymbol unit) {
        this.Team = Team;
        this.unit = unit;
    }

    protected boolean checkOverRange(final int x, final int y) {
        return XPosition.checkRange(x) && YPosition.checkRange(y);
    }

    public boolean checkSameTeam(final Team Team) {
        return this.Team == Team;
    }

    public boolean availableMove(final Position source, final Position target) {
        calculateAvailableDirectionalPositions(source);
        return containsPosition(target);
    }

    protected void calculateAvailableDirectionalPositions(final Position source) {
        initializeDirectionalPositions();
        for (Direction direction : directions()) {
            calculateAvailableDirectionalPositions(source, direction);
        }
    }

    protected void initializeDirectionalPositions() {
        directionalPositions = new HashMap<>();
    }

    protected abstract void calculateAvailableDirectionalPositions(final Position source, final Direction direction);

    protected boolean containsPosition(final Position position) {
        return directions().stream()
                .map(direction -> directionalPositions.get(direction))
                .anyMatch(positions -> positions.contains(position));
    }

    public void addDirectionalPosition(final Direction direction, final List<Position> positions) {
        directionalPositions.put(direction, positions);
    }

    public List<Position> calculateRoute(final Position target) {
        return directions().stream()
                .map(direction -> directionalPositions.get(direction))
                .filter(positions -> positions.contains(target))
                .findFirst()
                .orElse(null);
    }

    public String getSymbolByTeam() {
        return unit.symbol(Team);
    }

    public String symbol() {
        return unit.symbol();
    }

    protected abstract List<Direction> directions();

    @Override
    public String toString() {
        return "Piece{" +
                "Team=" + Team +
                ", unit=" + unit +
                '}';
    }
}
