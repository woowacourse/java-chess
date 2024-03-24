package chess.domain.piece;

import chess.domain.position.FileDifference;
import chess.domain.position.RankDifference;

public class EmptyPiece extends Piece {

    public EmptyPiece() {
        super(Color.EMPTY, (fileDifference, rankDifference) -> false);
    }

    @Override
    public boolean isCatchable(FileDifference fileDifference, RankDifference rankDifference) {
        return false;
    }
}
