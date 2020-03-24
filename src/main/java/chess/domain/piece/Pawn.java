package chess.domain.piece;

import chess.domain.player.Player;
import chess.domain.position.Position;

public class Pawn extends Piece {

    protected Pawn(Position position, Player player) {
        super(position, player);
    }

    @Override
    public void move(Position target) {

    }
}
