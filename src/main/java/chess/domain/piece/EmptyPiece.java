package chess.domain.piece;

import chess.domain.board.Paths2;
import chess.domain.position.Position2;

public class EmptyPiece implements Piece2 {

    @Override
    public Paths2 possiblePaths(Position2 currentPosition) {
        return null;
    }

    @Override
    public boolean isColor(PieceColor color) {
        return false;
    }

    @Override
    public boolean isNothing() {
        return false;
    }
}
