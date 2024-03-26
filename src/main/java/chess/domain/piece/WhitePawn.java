package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.DirectionJudge;
import chess.domain.position.Position;
import chess.domain.position.RowPosition;

import java.util.List;

import static chess.domain.position.Direction.*;

public final class WhitePawn extends Pawn {
    private static final List<Direction> FORWARD_DIRECTIONS = List.of(N, NW, NE);
    private static final RowPosition INITIAL_ROW = RowPosition.SIX;

    public WhitePawn() {
        super(Team.WHITE);
    }

    @Override
    boolean isInitialPawnRow(Position start) {
        return start.rowIs(INITIAL_ROW);
    }

    @Override
    boolean isForward(Position start, Position destination) {
        return FORWARD_DIRECTIONS.contains(DirectionJudge.judge(start, destination));
    }
}
