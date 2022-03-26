package domain.piece;

import domain.Player;
import domain.directions.BlackPawnDirections;

public class BlackPawn extends Pawn {

    private static final int START_LINE = 7;

    public BlackPawn(Player player) {
        super(player, new BlackPawnDirections(), START_LINE);
    }
}
