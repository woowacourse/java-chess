package domain.piece;

import domain.Player;
import domain.directions.RookDirections;

public class Rook extends MovableRangePiece {

    public Rook(final Player player) {
        super(player, PieceSymbol.ROOK, new RookDirections());
    }
}
