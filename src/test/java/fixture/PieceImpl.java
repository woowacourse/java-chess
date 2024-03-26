package fixture;

import domain.chess.Color;
import domain.chess.piece.Piece;
import domain.chess.piece.PieceStatus;
import domain.chess.Point;

import java.util.List;

public class PieceImpl extends Piece {
    public PieceImpl(final Point point, final Color color) {
        super(point, color);
    }

    @Override
    public PieceStatus getStatus() {
        throw new UnsupportedOperationException("사용할 수 없습니다.");
    }

    @Override
    public boolean canMove(final Point point, final List<Piece> pieceList) {
        throw new UnsupportedOperationException("사용할 수 없습니다.");
    }
}
