package domain.piece.type;

import java.util.List;

import domain.board.Square;
import domain.piece.Camp;
import domain.piece.Piece;

public class Empty extends Piece {

    public Empty() {
        super(Camp.NONE);
    }

    @Override
    public List<Square> fetchMovableCoordinate(Square currentSquare, Square targetSquare) {
        return null;
    }
}
