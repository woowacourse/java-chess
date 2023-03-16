package chess.piece;

import static chess.Movement.D;
import static chess.Movement.DL;
import static chess.Movement.DR;
import static chess.Movement.U;
import static chess.Movement.UL;
import static chess.Movement.UR;

import chess.Movement;
import chess.Path;
import chess.Position;
import java.util.Optional;

public class Pawn extends Piece {

    public Pawn(final Color color) {
        super(color);
    }

    public Path searchPathTo(Position from, Position to, Optional<Piece> destination) {
        Movement movement = to.convertMovement(from);

        if (destination.isEmpty() && (movement == U || movement == D)) {

            if (color.isWhite() && from.rank().value() == 2
                    && rankDifference(to, from) == 2) {
                Position wayPoint = new Position(from.file().value(), from.rank().value() + 1);

                return new Path(wayPoint);
            }

            if (color.isBlack() && from.rank().value() == 7
                    && rankDifference(to, from) == -2) {
                Position wayPoint = new Position(from.file().value(), from.rank().value() - 1);

                return new Path(wayPoint);
            }

            if (color.isWhite() && rankDifference(to, from) == 1) {
                return new Path();
            }

            if (color.isBlack() && rankDifference(to, from) == -1) {
                return new Path();
            }
            throw new IllegalStateException();
        }

        // 상대 말인 경우
        if (destination.get().color != color && (movement == UR || movement == UL || movement == DR
                || movement == DL)) {

            if (color.isBlack() && (movement == DR || movement == DL)) {
                return new Path();
            }

            if (color.isWhite() && (movement == UR || movement == UL)) {
                return new Path();
            }
        }
        throw new IllegalStateException();
    }

    private int rankDifference(final Position to, final Position from) {
        return to.rank().value() - from.rank().value();
    }
}
