package domain.piece;

import domain.Player;
import domain.directions.DirectionsGenerator;

public class Pawn extends SpecificLocationPiece {

    public Pawn(final Player player, final DirectionsGenerator directionsGenerator) {
        super(player, PieceSymbol.Pawn, directionsGenerator);
    }

}
