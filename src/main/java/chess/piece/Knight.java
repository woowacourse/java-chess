package chess.piece;

import static chess.Movement.DDL;
import static chess.Movement.DDR;
import static chess.Movement.LLD;
import static chess.Movement.LLU;
import static chess.Movement.RRD;
import static chess.Movement.RRU;
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

        int rankDifference = Math.abs(to.calculateRankBetween(from));
        int fileDifference = Math.abs(to.calculateFileBetween(from));

        if (rankDifference + fileDifference != 3) {
            throw new IllegalStateException();
        }

        if (!CAN_MOVE_DESTINATION.contains(movement)) {
            throw new IllegalStateException();
        }

        return new Path();
    }
}
