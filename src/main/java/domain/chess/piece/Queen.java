package domain.chess.piece;

import domain.chess.Color;
import domain.chess.Point;

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
