package chess.piece;

import chess.Movement;
import chess.Path;
import chess.Position;

import java.util.List;
import java.util.Optional;

import static chess.Movement.D;
import static chess.Movement.DL;
import static chess.Movement.DR;
import static chess.Movement.L;
import static chess.Movement.R;
import static chess.Movement.U;
import static chess.Movement.UL;
import static chess.Movement.UR;

public class King extends Piece {

    private static final List<Movement> CAN_MOVE_DESTINATION = List.of(U, D, R, L, UR, UL, DR, DL);

    public King(final Color color) {
        super(color);
    }

    @Override
    public Path searchPathTo(final Position from, final Position to, final Optional<Piece> destination) {
        destination.ifPresent(super::validateSameColor);

        Movement movement = to.convertMovement(from);

        if (!from.moveBy(movement).equals(to)) {
            throw new IllegalStateException(this.getClass().getSimpleName() + "은 한 칸만 이동할 수 있습니다.");
        }

        if (!CAN_MOVE_DESTINATION.contains(movement)) {
            throw new IllegalStateException(this.getClass().getSimpleName() + "이(가) 이동할 수 없는 경로입니다.");
        }

        return new Path();
    }
}
