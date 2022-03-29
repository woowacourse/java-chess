package chess.piece;

import chess.Player;

public class Blank extends Piece {

    public Blank(Player player, String symbol) {
        super(player, symbol);
    }

    @Override
    public double addTo(double score) {
        throw new IllegalArgumentException("[ERROR] 더 할 수 없습니다.");
    }
}
