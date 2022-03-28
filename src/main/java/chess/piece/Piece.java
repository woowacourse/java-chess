package chess.piece;

import chess.Direction;
import chess.Player;
import chess.Position;

import java.util.List;
import java.util.Map;

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

    public boolean canMove(Position source, Position target, Map<Position, Piece> board) {
        return true;
    }

    protected List<Direction> getDirection() {
        return null;
    }

    protected boolean isOpponent(Player player) {
        return !player.equals(this.player) && !this.player.equals(Player.NONE);
    }

    public boolean isKing() {
        return false;
    }

    public abstract double addTo(double score);

    public boolean isPawn() {
        return false;
    }
}
