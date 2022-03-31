package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Team;
import chess.domain.position.Position;

public class Knight extends OneStepPiece {

    private static final double KNIGHT_SCORE = 2.5;

    public Knight(Team team, Position position) {
        super(team, position);
    }

    @Override
    public double getScore() {
        return KNIGHT_SCORE;
    }

    @Override
    protected void validateIsPossibleDst(Position destination) {
        int colDiff = destination.getColDifference(position);
        int rowDiff = destination.getRowDifference(position);

        Direction.knightDirection().stream()
                .filter(direction -> isMatch(colDiff, rowDiff, direction))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 위치로 말이 움직일 수 없습니다."));
    }

    private boolean isMatch(int colDiff, int rowDiff, Direction direction) {
        return rowDiff == direction.getYDegree() && colDiff == direction.getXDegree();
    }

    @Override
    public boolean isBlank() {
        return false;
    }
}