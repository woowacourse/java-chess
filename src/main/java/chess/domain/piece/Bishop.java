package chess.domain.piece;

import chess.domain.player.Player;
import chess.domain.position.Position;

public class Bishop extends Piece {

    private static final String BLACK_BISHOP_UNICODE = "\u265D";
    private static final String WHITE_BISHOP_UNICODE = "\u2657";

    private Bishop(Position position, Player player) {
        super(position, player);
    }

    public static Piece of(Position position, Player player) {
        return new Bishop(position, player);
    }

    @Override
    public void move(Position target) {

    }

    @Override
    public String toString() {
        if (player == Player.BLACK) {
            return BLACK_BISHOP_UNICODE;
        }
        return WHITE_BISHOP_UNICODE;
    }
}
