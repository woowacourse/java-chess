package chess.piece;

import chess.chessBoard.Direction;
import chess.game.Player;
import chess.chessBoard.position.Position;

import java.util.List;
import java.util.Map;

public abstract class Piece {

    protected final Player player;
    private final String symbol;


    protected Piece(final Player player, final String symbol) {
        this.player = player;
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public boolean isSame(final Player player) {
        return player.equals(this.player);
    }

    public abstract boolean canMove(final Position source, final Position target, final Map<Position, Piece> board);

    protected List<Direction> getDirections() {
        return null;
    }

    protected boolean isOpponent(final Player player) {
        return !player.equals(this.player) && !this.player.equals(Player.NONE);
    }

    public boolean isKing() {
        return false;
    }

    public abstract double addTo(final double score);

    public boolean isPawn() {
        return false;
    }
}
