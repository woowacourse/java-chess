package domain.piece;

import domain.Player;

public class Piece {

    private final Player player;
    private final PieceSymbol pieceSymbol;

    public Piece(final Player player, final PieceSymbol pieceSymbol) {
        this.player = player;
        this.pieceSymbol = pieceSymbol;
    }

    public boolean isSamePlayer(Player player) {
        return this.player == player;
    }

    public String symbol() {
        return pieceSymbol.symbol(player);
    }
}
