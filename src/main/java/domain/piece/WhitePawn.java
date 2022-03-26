package domain.piece;

import domain.Player;
import domain.directions.WhitePawnDirections;

public class WhitePawn extends Pawn {

    private static final int START_LINE = 2;

    public WhitePawn(Player player) {
        super(player, new WhitePawnDirections(), START_LINE);
    }
}
