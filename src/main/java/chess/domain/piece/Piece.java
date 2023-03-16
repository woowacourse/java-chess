package chess.domain.piece;

import chess.domain.board.FileCoordinate;
import chess.domain.board.Position;
import chess.domain.board.RankCoordinate;
import java.util.List;

public abstract class Piece {

    private final Color color;

    protected Piece(Color color) {
        this.color = color;
    }

    abstract boolean canMove(Position sourcePosition, Position targetPosition, Color color);

    abstract List<Position> findPath(Position sourcePosition, Position targetPosition);

    abstract boolean isKing();

    public abstract boolean isEmpty();

    abstract Piece move();

    public boolean isSameTeam(Color color) {
        return this.color == color;
    }

    protected boolean isNotMyPosition(Position sourcePosition, Position targetPosition) {
        return !sourcePosition.equals(targetPosition);
    }

    protected boolean isDiagonal(Position sourcePosition, Position targetPosition) {
        FileCoordinate sourceFileCoordinate = sourcePosition.getFileCoordinate();
        FileCoordinate targetFileCoordinate = targetPosition.getFileCoordinate();

        RankCoordinate sourceRankCoordinate = sourcePosition.getRankCoordinate();
        RankCoordinate targetRankCoordinate = targetPosition.getRankCoordinate();
        int columnAbs = Math.abs(sourceFileCoordinate.getColumnNumber() - targetFileCoordinate.getColumnNumber());
        int rowAbs = Math.abs(sourceRankCoordinate.getRowNumber() - targetRankCoordinate.getRowNumber());
        return columnAbs == rowAbs;
    }

    protected boolean isStraight(Position sourcePosition, Position targetPosition) {
        return (sourcePosition.getFileCoordinate() == targetPosition.getFileCoordinate()
                || sourcePosition.getRankCoordinate() == targetPosition.getRankCoordinate());
    }

    protected int getStep(int nowFileCoordinate, int targetFileCoordinate) {
        if (nowFileCoordinate - targetFileCoordinate > 0) {
            return -1;
        }
        return 1;
    }

    public Color getColor() {
        return color;
    }
}
