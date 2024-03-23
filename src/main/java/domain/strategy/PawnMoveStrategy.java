package domain.strategy;

import domain.TeamColor;
import domain.board.Position;
import domain.board.Rank;

import java.util.Set;

public class PawnMoveStrategy implements MoveStrategy {
    private final TeamColor teamColor;

    public PawnMoveStrategy(final TeamColor teamColor) {
        this.teamColor = teamColor;
    }

    @Override
    public boolean isMovable(final Position source, final Position destination, final Set<Position> piecePositions) {
        if (teamColor == TeamColor.WHITE) {
            return isWhiteMovable(source, destination, piecePositions.contains(destination));
        }
        return isBlackMovable(source, destination, piecePositions.contains(destination));
    }

    private boolean isWhiteMovable(final Position source, final Position destination, boolean isExistOtherPiece) {
        int rowDiff = destination.rowIndex() - source.rowIndex();
        int colDiff = destination.columnIndex() - source.columnIndex();

        boolean isStraightMovable = (goUpWhite(rowDiff, colDiff) || goUpUpWhite(rowDiff, colDiff, source)) && !isExistOtherPiece;
        boolean isCrossMovable = goCrossWhite(rowDiff, colDiff) && isExistOtherPiece;

        return isStraightMovable || isCrossMovable;
    }

    private boolean goUpWhite(int rowDiff, int colDiff) {
        return rowDiff == -1 && colDiff == 0;
    }

    private boolean goUpUpWhite(int rowDiff, int colDiff, Position source) {
        return rowDiff == -2 && colDiff == 0 && isInitialPosition(source);
    }

    private boolean goCrossWhite(final int rowDiff, final int colDiff) {
        return rowDiff == -1 && Math.abs(colDiff) == 1;
    }


    private boolean isBlackMovable(final Position source, final Position destination, final boolean isExistOtherPiece) {
        int rowDiff = destination.rowIndex() - source.rowIndex();
        int colDiff = destination.columnIndex() - source.columnIndex();

        boolean isStraightMovable = (goUpBlack(rowDiff, colDiff) || goUpUpBlack(rowDiff, colDiff, source)) && !isExistOtherPiece;
        boolean isCrossMovable = goCrossBlack(rowDiff, colDiff) && isExistOtherPiece;

        return isStraightMovable || isCrossMovable;
    }

    private boolean goUpBlack(int rowDiff, int colDiff) {
        return rowDiff == 1 && colDiff == 0;
    }

    private boolean goUpUpBlack(int rowDiff, int colDiff, Position source) {
        return rowDiff == 2 && colDiff == 0 && isInitialPosition(source);
    }

    private boolean goCrossBlack(final int rowDiff, final int colDiff) {
        return rowDiff == 1 && Math.abs(colDiff) == 1;
    }

    public boolean isInitialPosition(final Position position) {
        if (teamColor == TeamColor.BLACK) {
            return position.rank() == Rank.SEVEN;
        }

        return position.rank() == Rank.TWO;
    }
}
