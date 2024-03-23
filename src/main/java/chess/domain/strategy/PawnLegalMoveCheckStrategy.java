package chess.domain.strategy;

import chess.domain.Direction;
import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import chess.domain.square.dto.SquareDifferent;

public class PawnLegalMoveCheckStrategy implements LegalMoveCheckStrategy {

    private static final int PAWN_FORWARD_INDEX = 1;
    private static final int PAWN_FIRST_FORWARD_INDEX = 2;
    private static final Rank WHITE_PAWN_FIRST_RANK = Rank.TWO;
    private static final Rank BLACK_PAWN_FIRST_RANK = Rank.SEVEN;

    private final BlockedPathCheckStrategy blockedPathCheckStrategy;

    public PawnLegalMoveCheckStrategy() {
        this.blockedPathCheckStrategy = new BlockedPathCheckStrategy();
    }

    @Override
    public boolean check(Square source, Square destination, Board board) {
        Piece sourcePiece = board.findPieceBySquare(source);
        Piece destinationPiece = board.findPieceBySquare(destination);
        SquareDifferent diff = source.calculateDiff(destination);
        Direction direction = Direction.findDirectionByDiff(diff);

        if (!direction.isDiagonal() && destinationPiece.isNotEmpty()) {
            return false;
        }

        int forwardIndex = selectIndexByColor(PAWN_FORWARD_INDEX, sourcePiece.isBlack());
        int firstForwardIndex = selectIndexByColor(PAWN_FIRST_FORWARD_INDEX, sourcePiece.isBlack());

        if (isFirstRank(source)) {
            if (!blockedPathCheckStrategy.check(source, destination, board)) {
                return false;
            }

            return pawnFirstMoveCondition(forwardIndex, diff, firstForwardIndex);
        }

        return pawnNormalMoveCondition(forwardIndex, diff);
    }

    private boolean isFirstRank(Square source) {
        return source.isSameRank(WHITE_PAWN_FIRST_RANK) || source.isSameRank(BLACK_PAWN_FIRST_RANK);
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
