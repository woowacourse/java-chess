package chess.piece;


import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.FileDifference;
import chess.domain.position.RankDifference;

public class TestPiece extends Piece {

    public TestPiece(Color color) {
        super(color, ((fileDifference, rankDifference) -> false));
    }

    @Override
    public boolean isCatchable(FileDifference fileDifference, RankDifference rankDifference) {
        return false;
    }
}
