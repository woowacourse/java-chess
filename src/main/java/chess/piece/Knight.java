package chess.piece;

import static chess.Movement.D;
import static chess.Movement.DDL;
import static chess.Movement.DDR;
import static chess.Movement.DL;
import static chess.Movement.DR;
import static chess.Movement.L;
import static chess.Movement.LLD;
import static chess.Movement.LLU;
import static chess.Movement.R;
import static chess.Movement.RRD;
import static chess.Movement.RRU;
import static chess.Movement.U;
import static chess.Movement.UL;
import static chess.Movement.UR;
import static chess.Movement.UUL;
import static chess.Movement.UUR;

import chess.Movement;
import chess.Path;
import chess.Position;
import java.util.List;
import java.util.Optional;

public class Knight extends Piece {

    private static final List<Movement> CAN_MOVE_DESTINATION =
            List.of(
                    UUR, UUL, RRU, RRD,
                    DDR, DDL, LLU, LLD
            );

    public Knight(final Color color) {
        super(color);
    }

    @Override
    public Path searchPathTo(final Position from, final Position to, final Optional<Piece> destination) {
        destination.ifPresent(super::validateSameColor);

        Movement movement = to.convertMovement(from);

        int rankDifference = Math.abs(to.rankDifference(from));
        int fileDifference = Math.abs(to.fileDifference(from));

        if (rankDifference + fileDifference != 3) {
            throw new IllegalStateException();
        }

        if (!CAN_MOVE_DESTINATION.contains(movement)) {
            throw new IllegalStateException();
        }

        return new Path();
    }
}
