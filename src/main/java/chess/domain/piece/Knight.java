package chess.domain.piece;

import static chess.domain.position.Movement.DDL;
import static chess.domain.position.Movement.DDR;
import static chess.domain.position.Movement.LLD;
import static chess.domain.position.Movement.LLU;
import static chess.domain.position.Movement.RRD;
import static chess.domain.position.Movement.RRU;
import static chess.domain.position.Movement.UUL;
import static chess.domain.position.Movement.UUR;

import chess.domain.position.Movement;
import chess.domain.position.Path;
import chess.domain.position.Position;
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
