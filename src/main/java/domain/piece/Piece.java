package domain.piece;

import domain.directions.Direction;
import domain.directions.DirectionsGenerator;
import domain.Player;
import domain.position.Position;
import java.util.List;

public abstract class Piece {

    private final Player player;
    private final PieceSymbol pieceSymbol;
    final List<Direction> directions;

    public Piece(final Player player, final PieceSymbol pieceSymbol, DirectionsGenerator directionsGenerator) {
        this.player = player;
        this.pieceSymbol = pieceSymbol;
        this.directions = directionsGenerator.generate();
    }

    public boolean isSamePlayer(Player player) {
        return this.player == player;
    }

    public String symbol() {
        return pieceSymbol.symbol(player);
    }

    public abstract List<Position> availableMovePositions(Position source);
}
