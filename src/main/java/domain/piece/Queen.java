package domain.piece;

import domain.Player;
import domain.piece.Piece;
import domain.piece.PieceSymbol;

public class Queen extends Piece {

    public Queen(final Player player) {
        super(player, PieceSymbol.Queen);
    }
}
