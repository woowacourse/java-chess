package chess.domain.piece;

import chess.domain.board.position.Movement;
import chess.domain.board.position.Path;
import chess.domain.board.position.Position;

import java.util.ArrayList;
import java.util.List;

import static chess.domain.board.position.Movement.D;
import static chess.domain.board.position.Movement.DL;
import static chess.domain.board.position.Movement.DR;
import static chess.domain.board.position.Movement.L;
import static chess.domain.board.position.Movement.R;
import static chess.domain.board.position.Movement.U;
import static chess.domain.board.position.Movement.UL;
import static chess.domain.board.position.Movement.UR;

public class Queen extends Piece {

    private static final List<Movement> CAN_MOVE_DESTINATION = List.of(U, D, R, L, UR, UL, DR, DL);

    public Queen(final Color color) {
        super(color);
    }

    @Override
    public Path searchPathTo(final Position from, final Position to, final Piece locatedPiece) {
        super.validateSameColor(locatedPiece);

        Movement movement = to.convertMovement(from);

        if (!CAN_MOVE_DESTINATION.contains(movement)) {
            throw new IllegalStateException(this.getClass().getSimpleName() + "이(가) 이동할 수 없는 경로입니다.");
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
