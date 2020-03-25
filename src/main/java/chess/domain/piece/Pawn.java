package chess.domain.piece;

import chess.domain.player.Player;
import chess.domain.position.Position;

public class Pawn extends Piece {

    private static final String BLACK_PAWN_UNICODE = "\u265F";
    private static final String WHITE_PAWN_UNICODE = "\u2659";

    private Pawn(Position position, Player player) {
        super(position, player);
    }

    public static Pawn of(Position position, Player player) {
        return new Pawn(position, player);
    }

    @Override
    public void move(Position target) {

    }

    @Override
    public String toString() {
        if (player == Player.BLACK) {
            return BLACK_PAWN_UNICODE;
        }
        return WHITE_PAWN_UNICODE;
    }
}
