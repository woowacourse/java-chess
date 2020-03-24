package chess.domain.piece;

import chess.domain.player.Player;
import chess.domain.position.Position;

public class King extends Piece {

    private King(Position position, Player player) {
        super(position, player);
    }

    public static Piece of(Position position, Player player) {
        return new King(position, player);
    }

    @Override
    public void move(Position target) {

    }
}
