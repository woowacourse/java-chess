package domain.piece;

import domain.Player;
import domain.piece.Piece;
import domain.piece.PieceSymbol;

public class Bishop extends Piece {

    public Bishop(final Player player) {
        super(player, PieceSymbol.Bishop);
    }
}
