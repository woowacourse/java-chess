package chess.board;

import chess.piece.Direction;

public class Position {

    private File file;
    private Rank rank;

    public Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public Direction getDirectionTo(Position targetPosition) {
        final int fileValuePoint = file.getValuePoint(targetPosition.file);
        final int rankValuePoint = rank.getValuePoint(targetPosition.rank);
        return Direction.from(fileValuePoint, rankValuePoint);
    }

    public double getSlope(Position targetPosition) {
        final int fileValueDiff = file.getValueDiff(targetPosition.file);
        final int rankValueDiff = rank.getValueDiff(targetPosition.rank);
        return Math.abs((double) fileValueDiff / rankValueDiff);
        // TODO: 2023/03/15 분모가 0이 되는 상황 예외처리
    }

    public int getMoveCount(final Position targetPosition, final Direction direction) {
        int moveCount = 0;
        if (direction.isHorizontalMovable()) {
            moveCount = file.getValueDiff(targetPosition.file);
        }
        if (direction.isVerticalMovable()) {
            moveCount = rank.getValueDiff(targetPosition.rank);
        }
        return moveCount;
    }

    public int getFile() {
        return file.getValue();
    }

    public int getRank() {
        return rank.getValue();
    }
}
