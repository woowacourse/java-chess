package domain.piece;

import domain.directions.Direction;
import domain.directions.DirectionsGenerator;
import domain.Player;
import domain.position.Column;
import domain.position.Position;
import domain.position.Row;
import java.util.ArrayList;
import java.util.List;

public abstract class Piece {

    private final Player player;
    private final PieceSymbol pieceSymbol;
    final List<Direction> directions;

    public Piece(final Player player, final PieceSymbol pieceSymbol,
        DirectionsGenerator directionsGenerator) {
        this.player = player;
        this.pieceSymbol = pieceSymbol;
        this.directions = directionsGenerator.generate();
    }

    abstract List<Position> calculateAvailablePosition(final Position source, final Direction direction);

    public boolean isAvailableMove(Position source, Position target){
        return availableMovePositions(source).contains(target);
    }
    public boolean isSamePlayer(Player player) {
        return this.player == player;
    }
    public boolean isSamePlayer(Piece comparePiece) {
        return comparePiece.isSamePlayer(player);
    }
    public String symbol() {
        return pieceSymbol.symbol(player);
    }

    public List<Position> availableMovePositions(Position source) {
        List<Position> positions = new ArrayList<>();
        for (Direction direction : directions) {
            positions.addAll(calculateAvailablePosition(source, direction));
        }
        return positions;
    }

    protected boolean checkOverRange(final int row, final int column) {
        return Row.isRowRange(row) && Column.isColumnRange(column);
    }

    public Player getPlayer() {
        return player;
    }
}
