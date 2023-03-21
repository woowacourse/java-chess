package chess.domain.piece;

import chess.domain.square.Direction;
import chess.domain.square.Square;

public class Pawn extends Piece {

    public Pawn(final Team team) {
        super(team);
    }

    // TODO: 코드 개선 시급
    @Override
    public Direction findDirection(Square current, Square destination) {
        int fileDifference = current.getFileDifference(destination);
        int rankDifference = current.getRankDifference(destination);
        if (team == Team.WHITE) {
            if (current.isRankTwo() && fileDifference == 0 && rankDifference == 2) {
                return Direction.UP;
            }
            return PieceDirection.WHITE_PAWN.findDirection(fileDifference, rankDifference);
        }
        if (current.isRankSeven() && fileDifference == 0 && rankDifference == -2) {
            return Direction.DOWN;
        }
        return PieceDirection.BLACK_PAWN.findDirection(fileDifference, rankDifference);
    }
}
