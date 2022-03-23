package domain.piece;

import domain.Player;
import domain.directions.QueenDirections;

public class Queen extends MovableRangePiece {

    public Queen(final Player player) {
        super(player, PieceSymbol.Queen, new QueenDirections());
    }
}
