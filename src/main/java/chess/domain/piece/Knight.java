package chess.domain.piece;

import chess.domain.position.Position;
import java.util.Map;

public class Knight extends Piece {

    public Knight(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position source, Position target, Map<Position, Piece> board) {
        int fileDifference = source.calculateFileDifference(target);
        int rankDifference = source.calculateRankDifference(target);

        if ((Math.abs(fileDifference) == 1 && Math.abs(rankDifference) == 2)
            || (Math.abs(fileDifference) == 2 && Math.abs(rankDifference) == 1)) {
            return board.get(target).doesNotExist() || board.get(target).hasOppositeColorFrom(this);
        }

        return false;
    }

    @Override
    public boolean exists() {
        return true;
    }
}
