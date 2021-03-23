package chess.domain.piece;

import java.util.List;

public class Empty extends Piece {

    public Empty() {
        super(PieceType.EMPTY, PieceColor.NOTHING);
    }

    @Override
    public List<Direction> directions() {
        return null;
    }
}
