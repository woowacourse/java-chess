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

    public Pawn(final Color color, final Position position) {
        super(color, position);
    }

    public Path searchPathTo(Position to, Optional<Piece> destination) {
        Movement movement = to.convertMovement(position);

        if (destination.isEmpty() && (movement == U || movement == D)) {

            if (color.isWhite() && position.rank().value() == 2
                    && rankDifference(to) == 2) {
                Position wayPoint = new Position(position.file().value(), position.rank().value() + 1);

                return new Path(wayPoint);
            }

            if (color.isBlack() && position.rank().value() == 7
                    && rankDifference(to) == -2) {
                Position wayPoint = new Position(position.file().value(), position.rank().value() - 1);

                return new Path(wayPoint);
            }

            if (color.isWhite() && rankDifference(to) == 1) {
                return new Path();
            }

            if (color.isBlack() && rankDifference(to) == -1) {
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

    private int rankDifference(final Position to) {
        return to.rank().value() - position.rank().value();
    }
}
