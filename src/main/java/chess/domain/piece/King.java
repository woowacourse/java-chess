package chess.domain.piece;

import chess.domain.player.Player;
import chess.domain.position.Position;

public class King extends Piece {

    private static final String BLACK_KING_UNICODE = "\u265A";
    private static final String WHITE_KING_UNICODE = "\u2654";

    private King(Position position, Player player) {
        super(position, player);
    }

    public static Piece of(Position position, Player player) {
        return new King(position, player);
    }

    @Override
    public void move(Position target) {

    }

    @Override
    public String toString() {
        if (player == Player.BLACK) {
            return BLACK_KING_UNICODE;
        }
        return WHITE_KING_UNICODE;
    }
}
