package domain.piece;

import domain.Player;
import domain.directions.BishopDirections;

public class Bishop extends MovableRangePiece {

    public Bishop(final Player player) {
        super(player, PieceSymbol.BISHOP, new BishopDirections());
    }
}
