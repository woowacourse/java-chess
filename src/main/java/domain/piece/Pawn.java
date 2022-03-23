package domain.piece;

import domain.Player;

public class Pawn extends Piece {

    public Pawn(final Player player) {
        super(player, PieceSymbol.Pawn);
    }
}
