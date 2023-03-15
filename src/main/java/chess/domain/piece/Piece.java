package chess.domain.piece;

import chess.domain.board.FileCoordinate;
import chess.domain.board.Position;
import chess.domain.board.RankCoordinate;

public abstract class Piece {

    private final Color color;

    protected Piece(Color color) {
        this.color = color;
    }

    abstract boolean canMove(Position sourcePosition, Position targetPosition);

    abstract boolean isKing();

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
        return Math.abs(sourceFileCoordinate.getColumnNumber() - targetFileCoordinate.getColumnNumber()) == Math.abs(
                sourceRankCoordinate.getRowNumber() - targetRankCoordinate.getRowNumber());
    }

    protected boolean isStraight(Position sourcePosition, Position targetPosition) {
        return (sourcePosition.getFileCoordinate() == targetPosition.getFileCoordinate()
                || sourcePosition.getRankCoordinate() == targetPosition.getRankCoordinate());
    }

    public Color getColor() {
        return color;
    }
}
