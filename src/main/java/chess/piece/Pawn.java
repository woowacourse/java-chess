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
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Pawn extends Piece {

    private static final Map<Color, Movement> CAN_MOVE_EMPTY_DESTINATION = Map.of(
            Color.BLACK, D,
            Color.WHITE, U
    );
    private static final Map<Color, List<Movement>> CAN_MOVE_ENEMY_DESTINATION = Map.of(
            Color.BLACK, List.of(DR, DL),
            Color.WHITE, List.of(UR, UL)
    );

    public Pawn(final Color color) {
        super(color);
    }

    public Path searchPathTo(Position from, Position to, Optional<Piece> destination) {
        Movement movement = to.convertMovement(from);

        if (destination.isEmpty() && movement == CAN_MOVE_EMPTY_DESTINATION.get(color)) {

            if (color.isWhite() && from.rank().value() == 2
                    && rankDifference(from, to) == 2) {
                final Position wayPoint = from.moveBy(U);

                return new Path(wayPoint);
            }

            if (color.isBlack() && from.rank().value() == 7
                    && rankDifference(from, to) == -2) {
                Position wayPoint = from.moveBy(D);

                return new Path(wayPoint);
            }

            return new Path();
        }
        // 상대 말인 경우
        if (destination.isPresent()
                && destination.get().color.isDifferentColor(color)
                && CAN_MOVE_ENEMY_DESTINATION.get(color).contains(movement)) {
            return new Path();
        }
        throw new IllegalStateException();
    }

    private int rankDifference(final Position from, final Position to) {
        return to.calculateRankBetween(from);
    }
}
