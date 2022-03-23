package domain.piece;

import domain.Player;
import domain.directions.WhitePawnDirections;

public class WhitePawn extends Pawn {

    public WhitePawn(Player player) {
        super(player, new WhitePawnDirections());
    }
}
