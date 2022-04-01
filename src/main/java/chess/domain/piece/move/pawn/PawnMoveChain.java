package chess.domain.piece.move.pawn;

import chess.domain.board.Point;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

import java.util.Map;

public abstract class PawnMoveChain {

    protected final PawnSupport support;

    public PawnMoveChain(PawnSupport support) {
        this.support = support;
    }

    protected boolean isEmptyPoint(Map<Point, Piece> pointPieces, Point point) {
        Piece piece = pointPieces.get(point);
        return piece.isSameType(PieceType.EMPTY);
    }

    public abstract void move(Map<Point, Piece> pointPieces, Point from, Point to);
}
