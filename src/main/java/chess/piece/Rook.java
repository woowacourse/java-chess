package chess.piece;

import chess.Player;

public class Rook extends Piece {

    private static final double SCORE = 5;

    public Rook(Player player, String symbol) {
        super(player, symbol);
    }

    @Override
    public double addTo(double score) {
        return score + SCORE;
    }
}
