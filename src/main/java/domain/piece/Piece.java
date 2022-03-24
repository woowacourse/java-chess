package domain.piece;

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

    public void initializePosition() {
        positions = new ArrayList<>();
    }

    public boolean checkSameTeamColor(TeamColor teamColor) {
        return this.teamColor == teamColor;
    }

    public boolean checkOverRange(final int x, final int y) {
        return XPosition.checkRange(x) && YPosition.checkRange(y);
    }

    public boolean availableMove(Position source, Position target) {
        calculateAvailablePositions(source);
        return positions.contains(target);
    }

    public void calculateAvailablePositions(final Position source) {
        initializePosition();
        for (Direction direction : directions()) {
            calculateAvailablePosition(source, direction);
        }
    }

    abstract void calculateAvailablePosition(final Position source, final Direction direction);

    public abstract List<Direction> directions();

    public String symbol() {
        return unit.symbol(teamColor);
    }

    public void positionAdd(Position position) {
        positions.add(position);
    }

    @Override
    public String toString() {
        return "Piece{" +
                "teamColor=" + teamColor +
                ", unit=" + unit +
                '}';
    }
}
