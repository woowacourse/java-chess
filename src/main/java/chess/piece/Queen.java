package chess.piece;

import static chess.Movement.D;
import static chess.Movement.DL;
import static chess.Movement.DR;
import static chess.Movement.L;
import static chess.Movement.R;
import static chess.Movement.U;
import static chess.Movement.UL;
import static chess.Movement.UR;

import chess.Movement;
import chess.Path;
import chess.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Queen extends Piece {


    private static final List<Movement> CAN_MOVE_DESTINATION = List.of(U, D, R, L, UR, UL, DR, DL);

    public Queen(final Color color) {
        super(color);
    }

    @Override
    public Path searchPathTo(final Position from, final Position to, final Optional<Piece> destination) {
        Movement movement = to.convertMovement(from);

        if (!CAN_MOVE_DESTINATION.contains(movement)) {
            throw new IllegalStateException();
        }

        Position next = from;
        List<Position> positions = new ArrayList<>();

        while (true) {
            next = next.moveBy(movement);
            if (next.equals(to)) {
                break;
            }
            positions.add(next);
        }

        return new Path(positions);
    }
}
