package chess.domain.piece;

import chess.domain.player.Player;
import chess.domain.position.Position;

public class King extends Piece {

    protected King(Position position, Player player) {
        super(position, player);
    }

    @Override
    public void move(Position target) {

    }
}
