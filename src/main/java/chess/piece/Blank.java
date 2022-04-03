package chess.piece;

import chess.game.Player;
import chess.chessBoard.position.Position;

import java.util.Map;

public class Blank extends Piece {

    public Blank(Player player, String symbol) {
        super(player, symbol);
    }

    @Override
    public boolean canMove(Position source, Position target, Map<Position, Piece> board) {
        return false;
    }

    @Override
    public double addTo(double score) {
        return score;
    }
}
