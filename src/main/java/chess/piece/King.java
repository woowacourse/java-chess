package chess.piece;

import chess.position.Position;

import java.util.List;

public class King extends Piece {
    private static final String INITIAL_CHARACTER = "K";

    public King(Team team) {
        super(team);
    }

    @Override
    public List<Position> findReachablePositions(Position start, Position end) {
        if (start.isNotDistanceOneSquare(end)) {
            throw new IllegalArgumentException("해당 위치로 이동할 수 없습니다.");
        }
        //구현
        return null;
    }

    @Override
    protected String getInitialCharacter() {
        return INITIAL_CHARACTER;
    }
}
