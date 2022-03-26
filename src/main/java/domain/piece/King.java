package domain.piece;

import domain.Player;
import domain.directions.KingDirections;

public class King extends SpecificLocationPiece {

    public King(final Player player) {
        super(player, PieceSymbol.King, new KingDirections());
    }
}
