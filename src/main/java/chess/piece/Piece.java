package chess.piece;

import chess.Player;
import chess.Position;
import chess.direction.route.Route;

public abstract class Piece {

    protected final Player player;
    private final String symbol;


    protected Piece(Player player, String symbol) {
        this.player = player;
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public boolean isSame(Player player) {
        return player.equals(this.player);
    }

    public boolean isSame(Piece otherPiece) {
        return otherPiece.player.equals(this.player);
    }

    public boolean isKing() {
        return false;
    }

    public abstract double addTo(double score);

    public boolean isPawn() {
        return false;
    }

    public Route findRoute(Position source, Position target) {
        return null;
    }
}
