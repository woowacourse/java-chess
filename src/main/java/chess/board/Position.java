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

    public int getFile() {
        return file.getValue();
    }

    public int getRank() {
        return rank.getValue();
    }
}
