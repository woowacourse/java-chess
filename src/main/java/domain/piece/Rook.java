package domain.piece;

import domain.Player;
import domain.piece.Piece;
import domain.piece.PieceSymbol;

public class Rook extends Piece {

    public Rook(final Player player) {
        super(player, PieceSymbol.Rook);
    }
}
