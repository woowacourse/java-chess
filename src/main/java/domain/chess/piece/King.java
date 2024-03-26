package domain.chess.piece;

import domain.chess.Color;
import domain.chess.Point;

import java.util.List;

public class King extends Piece {
    public King(final Point point, final Color color) {
        super(point, color);
    }

    @Override
    public PieceStatus getStatus() {
        return PieceStatus.KING;
    }

    public boolean canMove(final Point movePoint, final List<Piece> pieceList) {
        return canMovePointOne(movePoint) && hasEnemyPieceOrEmpty(movePoint, new Pieces(pieceList));
    }
}
