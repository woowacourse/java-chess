package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import chess.domain.position.RowPosition;

import java.util.List;

import static chess.domain.position.Direction.*;

public enum Team {
    // TODO 폰의 정보가 흘러 나온 것은 아닌지 고민해보기
    WHITE(List.of(N, NW, NE), RowPosition.SIX),
    BLACK(List.of(S, SW, SE), RowPosition.ONE),
    EMPTY(null, null);

    private final List<Direction> forwardDirections;
    private final RowPosition initialPawnRow;

    Team(List<Direction> forwardDirections, RowPosition initialPawnRow) {
        this.forwardDirections = forwardDirections;
        this.initialPawnRow = initialPawnRow;
    }

    public boolean isForward(Direction direction) {
        return forwardDirections.contains(direction);
    }

    public boolean isInitialPawnRow(Position position) {
        return position.rowIs(initialPawnRow);
    }
}
