package chess.piece;

import chess.game.Player;
import chess.chessBoard.position.Position;

import java.util.Map;

public final class Blank extends Piece {

    public Blank(final Player player, final String symbol) {
        super(player, symbol);
    }

    @Override
    public boolean canMove(final Position source, final Position target, final Map<Position, Piece> board) {
        return false;
    }

    @Override
    public double addTo(final double score) {
        return score;
    }
}
