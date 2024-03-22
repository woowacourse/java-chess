package chess.domain.piece;

import chess.domain.position.Position;

public class EmptyPiece extends Piece {

    public EmptyPiece() {
        super(Color.EMPTY, (fileDifference, rankDifference) -> false);
    }

    @Override
    public boolean isCatchable(Position from, Position to) {
        return false;
    }
}
