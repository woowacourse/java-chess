package domain.piece;

public abstract class Piece {

    private final Player player;
    private final Unit unit;

    public Piece(final Player player, final Unit unit) {
        this.player = player;
        this.unit = unit;
    }

    public Player getPlayer() {
        return player;
    }

    public String getSymbol() {
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
