package chess.domain.piece;

import chess.domain.player.Player;
import chess.domain.position.Position;

public class Queen extends Piece {

    private Queen(Position position, Player player) {
        super(position, player);
    }

    public static Piece of(Position position, Player player) {
        return new Queen(position, player);
    }

    @Override
    public void move(Position target) {

    }
}
