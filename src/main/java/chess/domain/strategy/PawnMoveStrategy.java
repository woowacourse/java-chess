package chess.domain.strategy;

import chess.domain.Direction;
import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Square;
import chess.dto.SquareDifferent;

public class PawnMoveStrategy implements MoveStrategy {

    private static final int PAWN_FORWARD_INDEX = 1;
    private static final int PAWN_FIRST_FORWARD_INDEX = 2;
    private static final String PAWN_CANNOT_CATCH_STRAIGHT_ERROR = "폰은 직선 경로로 상대 말을 잡을 수 없습니다.";

    private final PathFindStrategy pathFindStrategy;

    public PawnMoveStrategy() {
        this.pathFindStrategy = new PathFindStrategy();
    }

    @Override
    public boolean check(Square source, Square destination, Board board) {
        Piece sourcePiece = board.findPieceBySquare(source);
        Piece destinationPiece = board.findPieceBySquare(destination);
        SquareDifferent diff = source.calculateDiff(destination);
        Direction direction = Direction.findDirectionByDiff(diff);

        if (!direction.isDiagonal() && destinationPiece.isNotEmpty()) {
            throw new IllegalArgumentException(PAWN_CANNOT_CATCH_STRAIGHT_ERROR);
        }

        int forwardIndex = selectIndexByColor(PAWN_FORWARD_INDEX, sourcePiece.isBlack());
        int firstForwardIndex = selectIndexByColor(PAWN_FIRST_FORWARD_INDEX, sourcePiece.isBlack());

        if (source.isPawnStartSquare()) {
            if (!pathFindStrategy.check(source, destination, board)) {
                return false;
            }

            return pawnFirstMoveCondition(forwardIndex, diff, firstForwardIndex);
        }

        return pawnNormalMoveCondition(forwardIndex, diff);
    }

    private int selectIndexByColor(int index, boolean isBlack) {
        if (isBlack) {
            return index * -1;
        }

        return index;
    }

    private boolean pawnFirstMoveCondition(int forwardIndex, SquareDifferent diff, int firstForwardIndex) {
        return checkDiagonal(forwardIndex, diff.rankDiff(), diff.fileDiff())
                || checkCanForward(firstForwardIndex, diff.rankDiff(), diff.fileDiff())
                || checkCanForward(forwardIndex, diff.rankDiff(), diff.fileDiff());
    }

    private boolean pawnNormalMoveCondition(int forwardIndex, SquareDifferent diff) {
        return checkCanForward(forwardIndex, diff.rankDiff(), diff.fileDiff())
                || checkDiagonal(forwardIndex, diff.rankDiff(), diff.fileDiff());
    }


    private boolean checkCanForward(int forwardIndex, int rankDiff, int fileDiff) {
        return rankDiff == forwardIndex && fileDiff == 0;
    }

    private boolean checkDiagonal(int forwardIndex, int rankDiff, int fileDiff) {
        return rankDiff == forwardIndex && Math.abs(fileDiff) == 1;
    }
}
