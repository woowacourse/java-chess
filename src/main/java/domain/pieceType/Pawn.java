package domain.pieceType;

import domain.Color;
import domain.Square;
import java.util.List;

public class Pawn extends Piece {

    private static final PieceType PIECE_TYPE = PieceType.PAWN;

    public Pawn(final Color color) {
        super(color);
    }

    @Override
    public List<Square> calculatePath(final Square source, final Square target) {
        return List.of();
    }

    @Override
    public PieceType getPieceType() {
        return PIECE_TYPE;
    }
}
