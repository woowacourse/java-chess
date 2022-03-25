package chess.piece;

import chess.Player;

public abstract class Piece {

    private final Player player;
    private final String symbol;


    protected Piece(Player player, String symbol) {
        this.player = player;
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
