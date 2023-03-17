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

    private static final int MANHATTAN_DISTANCE = 3;

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

        if (calculateManhattanDistanceBetween(from, to) != MANHATTAN_DISTANCE) {
            throw new IllegalStateException(this.getClass().getSimpleName() + "은 한 칸 직진, 한 칸 대각선으로만 이동할 수 있습니다.");
        }

        if (!CAN_MOVE_DESTINATION.contains(movement)) {
            throw new IllegalStateException(this.getClass().getSimpleName() + "이(가) 이동할 수 없는 경로입니다.");
        }

        return new Path();
    }

    private int calculateManhattanDistanceBetween(Position from, Position to) {
        final int rankDifference = Math.abs(to.calculateRankBetween(from));
        final int fileDifference = Math.abs(to.calculateFileBetween(from));

        return rankDifference + fileDifference;
    }
}
