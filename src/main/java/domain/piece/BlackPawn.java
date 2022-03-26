package domain.piece;

import domain.directions.BlackPawnDirections;
import domain.Player;

public class BlackPawn extends Pawn {

    private static final int START_LINE = 7;

    public BlackPawn(Player player) {
        super(player, new BlackPawnDirections(), START_LINE);
    }
}
