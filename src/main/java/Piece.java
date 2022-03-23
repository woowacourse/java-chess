public class Piece {

    private final Player player;
    private final PieceSymbol pieceSymbol;

    public Piece(final Player player, final PieceSymbol pieceSymbol) {
        this.player = player;
        this.pieceSymbol = pieceSymbol;
    }

    public Player getPlayer() {
        return player;
    }

    public String symbol() {
        return pieceSymbol.symbol(player);
    }
}
