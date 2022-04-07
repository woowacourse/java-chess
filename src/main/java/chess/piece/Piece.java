package chess.piece;

import chess.chessboard.position.Direction;
import chess.game.Player;
import chess.chessboard.position.Position;

import java.util.List;
import java.util.Map;

public abstract class Piece {

    protected final Player player;
    private final Symbol symbol;


    protected Piece(final Player player, final Symbol symbol) {
        this.player = player;
        this.symbol = symbol;
    }

    public Character getSymbol() {
        return symbol.getSymbol(player);
    }

    public String getImageName() {
        return symbol.getImageName(player);
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

    public boolean isBlank() {
        return false;
    }
}
