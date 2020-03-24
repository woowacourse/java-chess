package chess.domain.piece;

import chess.domain.player.Player;
import chess.domain.position.Position;

public class Pawn extends Piece {

    private Pawn(Position position, Player player) {
        super(position, player);
    }

    public static Pawn of(Position position, Player player) {
        return new Pawn(position, player);
    }

    @Override
    public void move(Position target) {

    }
}
