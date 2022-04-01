package chess.domain.board.move;

import chess.domain.board.Position;
import chess.domain.piece.Piece;

public final class Angle {
    private Angle() {
    }

    public static boolean validate(Piece piece, Position from, Position to) {
        int dx = from.dx(to);
        int dy = from.dy(to);
        int angle = (int) (Math.atan2(dy, dx) * (180.0 / Math.PI));

        System.out.println(angle);
        return true;
    }
}
