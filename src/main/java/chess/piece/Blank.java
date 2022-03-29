package chess.piece;

import chess.Player;
import chess.Position;
import java.util.Map;

public class Blank extends Piece {

    public Blank(Player player, String symbol) {
        super(player, symbol);
    }

    @Override
    public double addTo(double score) {
        throw new IllegalArgumentException("[ERROR] 더 할 수 없습니다.");
    }

    @Override
    public boolean canMove(Position source, Position target, Map<Position, Piece> board) {
        return false;
    }
}
