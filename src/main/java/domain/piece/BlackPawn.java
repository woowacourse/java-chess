package domain.piece;

import domain.directions.BlackPawnDirections;
import domain.Player;

public class BlackPawn extends Pawn {

    public BlackPawn(Player player) {
        super(player, new BlackPawnDirections());
    }
}
