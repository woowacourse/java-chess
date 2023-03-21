package chess.domain.piece;

import chess.domain.square.Direction;
import chess.domain.square.Square;

public class Bishop extends Piece {

    public Bishop(final Team team) {
        super(team);
    }

    @Override
    public Direction findDirection(final Square current, final Square destination) {
        int fileDifference = current.getFileDifference(destination);
        int rankDifference = current.getRankDifference(destination);
        return PieceDirection.DIAGONAL.findDirection(fileDifference, rankDifference);
    }
}
