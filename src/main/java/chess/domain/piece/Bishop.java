package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Position;
import chess.domain.Team;

public class Bishop extends JumpPiece {

    private static final double BISHOP_SCORE = 3;

    public Bishop(Team team, Position position) {
        super(team, position);
    }

    @Override
    public double getScore() {
        return BISHOP_SCORE;
    }

    @Override
    protected Direction findDirection(Position destination) {
        int colDiff = destination.getCol().getDifference(position.getCol());
        int rowDiff = destination.getRow().getDifference(position.getRow());

        return Direction.diagonalDirection().stream()
                .filter(direction -> isMatch(colDiff, rowDiff, direction))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 위치로 말이 움직일 수 없습니다."));
    }

    private boolean isMatch(int colDiff, int rowDiff, Direction direction) {
        return colDiff * direction.getXDegree() == rowDiff * direction.getYDegree()
                && rowDiff * direction.getYDegree() > 0;
    }
}
