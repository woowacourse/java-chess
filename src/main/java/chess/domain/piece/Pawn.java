package chess.domain.piece;

import chess.domain.square.Direction;
import chess.domain.square.Square;

public class Pawn extends Piece {

    public Pawn(final Color color) {
        super(color);
    }

    // TODO: 적이 있는 경우에만 대각 이동 가능하게 하기
    // TODO: 더블폰푸시 길막인 상태에서 불가하게 하기
    @Override
    public Direction findDirection(Square current, Square destination) {
        int fileDifference = current.getFileDifference(destination);
        int rankDifference = current.getRankDifference(destination);
        if (color.equals(Color.WHITE)) {
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
