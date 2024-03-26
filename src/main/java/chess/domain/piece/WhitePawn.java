package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.DirectionJudge;
import chess.domain.position.Position;
import chess.domain.position.RowPosition;

import java.util.List;

import static chess.domain.position.Direction.*;

public final class WhitePawn extends Pawn {
    private static final List<Direction> FORWARD_DIRECTIONS = List.of(UP, UP_LEFT, UP_RIGHT);
    private static final RowPosition INITIAL_ROW = new RowPosition(6);

    public WhitePawn() {
        super(Team.WHITE);
    }

    @Override
    boolean isInitialPawnRow(Position start) {
        return start.isSameRowWith(INITIAL_ROW);
    }

    @Override
    boolean isForward(Position start, Position destination) {
        return FORWARD_DIRECTIONS.contains(DirectionJudge.judge(start, destination));
    }
}
