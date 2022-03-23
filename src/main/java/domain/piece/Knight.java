package domain.piece;

import domain.Player;
import domain.directions.KnightDirections;

public class Knight extends SpecificLocationPiece {

    public Knight(final Player player) {
        super(player, PieceSymbol.Knight, new KnightDirections());
    }
}
