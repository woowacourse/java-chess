package chess.domain.piece;

import chess.domain.player.Player;
import chess.domain.position.Position;

public class Queen extends Piece {

    protected Queen(Position position, Player player) {
        super(position, player);
    }

    @Override
    public void move(Position target) {

    }
}
