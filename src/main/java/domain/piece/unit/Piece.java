package domain.piece.unit;

import domain.piece.property.PieceSymbol;
import domain.piece.property.TeamColor;
import domain.position.Position;
import domain.position.XPosition;
import domain.position.YPosition;
import domain.utils.Direction;
import java.util.ArrayList;
import java.util.List;

public abstract class Piece {

    private final TeamColor teamColor;
    private final PieceSymbol unit;

    private List<Position> positions;

    public Piece(final TeamColor teamColor, final PieceSymbol unit) {
        this.teamColor = teamColor;
        this.unit = unit;
    }

    protected void initializePosition() {
        positions = new ArrayList<>();
    }

    protected boolean checkOverRange(final int x, final int y) {
        return XPosition.checkRange(x) && YPosition.checkRange(y);
    }

    public boolean checkSameTeamColor(final TeamColor teamColor) {
        return this.teamColor == teamColor;
    }

    public boolean availableMove(final Position source, final Position target) {
        calculateAvailablePositions(source);
        return containsTarget(target);
    }

    public void positionAdd(final Position position) {
        positions.add(position);
    }

    protected boolean containsTarget(final Position target) {
        return positions.contains(target);
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
