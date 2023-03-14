package domain.piece.type;

import java.util.List;
import java.util.Set;

import domain.board.Square;
import domain.piece.Camp;
import domain.piece.Piece;

public class Pawn extends Piece {

    public Pawn(Camp camp) {
        super(camp);
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public List<Square> fetchMovableCoordinate(Square currentSquare, Square targetSquare) {
        return null;
    }
}
