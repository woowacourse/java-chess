package domain.piece;

import domain.piece.property.PieceSymbol;
import domain.piece.property.TeamColor;
import domain.position.Position;
import domain.utils.Direction;
import java.util.List;

public abstract class Piece {

    private final TeamColor teamColor;
    private final PieceSymbol unit;

    public Piece(final TeamColor teamColor, final PieceSymbol unit) {
        this.teamColor = teamColor;
        this.unit = unit;
    }

    public boolean checkSameTeamColor(TeamColor teamColor) {
        return this.teamColor == teamColor;
    }

    abstract boolean availableMove(Position source, Position target);

    public abstract List<Direction> directions();

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
