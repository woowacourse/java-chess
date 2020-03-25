package chess.domain.piece;

import chess.domain.player.Player;
import chess.domain.position.Position;

public class Rook extends Piece {

    private static final String BLACK_ROOK_UNICODE = "\u265C";
    private static final String WHITE_ROOK_UNICODE = "\u2656";

    private Rook(Position position, Player player) {
        super(position, player);
    }

    public static Rook of(Position position, Player player) {
        return new Rook(position, player);
    }

    @Override
    public void move(Position target) {

    }

    @Override
    public String toString() {
        if (player == Player.BLACK) {
            return BLACK_ROOK_UNICODE;
        }
        return WHITE_ROOK_UNICODE;
    }
}
