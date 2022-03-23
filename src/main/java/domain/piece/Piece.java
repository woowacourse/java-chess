package domain.piece;

import domain.position.Position;

public abstract class Piece {

    private final Player player;
    private final Unit unit;

    public Piece(final Player player, final Unit unit) {
        this.player = player;
        this.unit = unit;
    }

    public boolean checkSamePlayer(Player player) {
        return this.player == player;
    }

    public String symbol() {
        return unit.getSymbol(player);
    }

    @Override
    public String toString() {
        return "Piece{" +
                "player=" + player +
                ", unit=" + unit +
                '}';
    }
}
