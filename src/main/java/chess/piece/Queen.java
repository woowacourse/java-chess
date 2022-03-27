package chess.piece;

import chess.position.Position;
import java.util.List;

public class Queen extends Piece {

    public Queen(Color color, Position position) {
        super(color, position);
    }

    @Override
    protected Piece createNewPiece(Position to) {
        return new Queen(getColor(), to);
    }

    @Override
    protected boolean isPossibleMovement(Position to, List<Piece> pieces) {
        return getPosition().isDiagonalWay(to) || getPosition().isVerticalWay(to)
            || getPosition().isHorizontalWay(to);
    }
}
