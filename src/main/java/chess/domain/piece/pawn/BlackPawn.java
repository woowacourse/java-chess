package chess.domain.piece.pawn;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceDirection;
import chess.domain.piece.PieceType;
import chess.domain.square.Direction;
import chess.domain.square.Square;

public class BlackPawn extends Piece {

    public BlackPawn() {
        super(Color.BLACK, PieceType.PAWN);
    }

    @Override
    public Direction findDirection(final Square current, final Square destination) {
        final int fileDifference = current.getFileDifference(destination);
        final int rankDifference = current.getRankDifference(destination);
        if (current.isRankSeven() && fileDifference == 0 && rankDifference == -2) {
            return Direction.DOWN;
        }
        return PieceDirection.BLACK_PAWN.findDirection(fileDifference, rankDifference);
    }
}
