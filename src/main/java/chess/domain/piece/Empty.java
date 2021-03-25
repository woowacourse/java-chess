package chess.domain.piece;

import java.util.ArrayList;
import java.util.List;

public class Empty extends LimitedMovePiece {

    public Empty() {
        super(PieceType.EMPTY, PieceColor.NOTHING);
    }

    @Override
    public List<Direction> directions() {
        return new ArrayList<>();
    }
}
