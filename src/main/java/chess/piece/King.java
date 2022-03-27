package chess.piece;

import chess.position.Position;
import java.util.List;

public class King extends Piece{

    public King(Color color, Position position) {
        super(color, position);
    }

    @Override
    protected Piece createNewPiece(Position to) {
        return new King(getColor(), to);
    }

    @Override
    protected boolean isPossibleMovement(Position to, List<Piece> pieces) {
        return getPosition().isAdjacent(to);
    }
}
