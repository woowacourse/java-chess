package chess.domain.piece;

import chess.domain.board.position.Movement;
import chess.domain.board.position.Path;
import chess.domain.board.position.Position;

import java.util.List;

import static chess.domain.board.position.Movement.DDL;
import static chess.domain.board.position.Movement.DDR;
import static chess.domain.board.position.Movement.LLD;
import static chess.domain.board.position.Movement.LLU;
import static chess.domain.board.position.Movement.RRD;
import static chess.domain.board.position.Movement.RRU;
import static chess.domain.board.position.Movement.UUL;
import static chess.domain.board.position.Movement.UUR;

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
    public Path searchPathTo(final Position from, final Position to, final Piece destination) {
        super.validateSameColor(destination);

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
