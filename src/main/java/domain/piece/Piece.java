package domain.piece;

import domain.position.Position;
import domain.position.XPosition;
import domain.position.YPosition;
import domain.utils.Direction;
import java.util.ArrayList;
import java.util.List;

public abstract class Piece {

    private final Player player;
    private final PieceSymbol unit;

    public Piece(final Player player, final PieceSymbol unit) {
        this.player = player;
        this.unit = unit;
    }

    public boolean checkSamePlayer(Player player) {
        return this.player == player;
    }

    abstract boolean availableMove(Position source, Position target);

    abstract List<Direction> directions();

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
