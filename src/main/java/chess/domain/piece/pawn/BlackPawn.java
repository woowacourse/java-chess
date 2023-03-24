package chess.domain.piece.pawn;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceDirection;
import chess.domain.piece.PieceType;
import chess.domain.square.Direction;
import chess.domain.square.Square;

public class BlackPawn extends Piece {

    private static final int DOUBLE_PAWN_PUSH_FILE_DIFFERENCE = 0;
    private static final int DOUBLE_PAWN_PUSH_RANK_DIFFERENCE = -2;

    public BlackPawn() {
        super(Color.BLACK, PieceType.PAWN);
    }

    @Override
    public Direction findDirection(final Square current, final Square destination) {
        final int fileDifference = current.getFileDifference(destination);
        final int rankDifference = current.getRankDifference(destination);
        if (isDoublePawnPush(current, destination)) {
            return Direction.DOWN;
        }
        return PieceDirection.findBlackPawnDirection(fileDifference, rankDifference);
    }

    private boolean isDoublePawnPush(final Square current, final Square destination) {
        if (!current.isRankSeven()) {
            return false;
        }
        return current.getFileDifference(destination) == DOUBLE_PAWN_PUSH_FILE_DIFFERENCE &&
                current.getRankDifference(destination) == DOUBLE_PAWN_PUSH_RANK_DIFFERENCE;
    }
}
