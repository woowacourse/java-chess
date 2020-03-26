package chess.piece;

import chess.position.Position;

import java.util.List;

public class Bishop extends Piece {
    private static final String INITIAL_CHARACTER = "B";

    public Bishop(Team team) {
        super(team);
    }

    @Override
    public List<Position> findReachablePositions(Position start, Position end) {
        if (start.isNotDiagonal(end)) {
            throw new IllegalArgumentException("해당 위치로 이동할 수 없습니다.");
        }
        return Position.findDiagonalTrace(start, end);
    }

    @Override
    protected String getInitialCharacter() {
        return INITIAL_CHARACTER;
    }
}
