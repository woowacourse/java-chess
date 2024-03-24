package domain.piece.kind;

import domain.piece.Piece;
import domain.piece.Pieces;
import domain.piece.attribute.Color;
import domain.piece.attribute.point.Point;

import java.util.List;

public class Queen extends Piece {
    public Queen(final Point point, final Color color) {
        super(point, color);
    }

    @Override
    public PieceStatus getStatus() {
        return PieceStatus.QUEEN;
    }

    public boolean canMove(final Point movePoint, final List<Piece> pieceList) {
        final Pieces pieces = new Pieces(pieceList);
        return notExistPieceInPath(movePoint, pieces) && hasEnemyPieceOrEmpty(movePoint, pieces);
    }
}
