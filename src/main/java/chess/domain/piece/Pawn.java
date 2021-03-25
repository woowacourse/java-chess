package chess.domain.piece;

import chess.domain.position.Position;
import java.util.List;

public class Pawn extends LimitedMovePiece {

    public Pawn(PieceColor pieceColor) {
        super(PieceType.PAWN, pieceColor);
    }

    @Override
    public List<Direction> directions() {
        if (pieceColor.equals(PieceColor.WHITE)) {
            return Direction.whitePawnDirection();
        }
        return Direction.blackPawnDirection();
    }

    public boolean isMovable(Position from, Position to, Piece target) {
        if (isEnemy(target)) {
            return canAttack(from, to);
        }
        if (from.isBlackPawnStartLine() || from.isWhitePawnStartLine()) {
            return canGoOneCell(from, to) || canGoTwoCell(from, to, target);
        }
        return canGoOneCell(from, to);
    }

    private boolean canAttack(Position from, Position to) {
        if (pieceColor.equals(PieceColor.WHITE)) {
            return from.isDiagonal(to) && from.isLowerTo(to);
        }
        return from.isDiagonal(to) && from.isUpperTo(to);
    }

    private boolean canGoOneCell(Position from, Position to) {
        return from.rowGap(to) == 1 && isHeadingForward(from, to);
    }

    private boolean canGoTwoCell(Position from, Position to, Piece target) {
        return from.rowGap(to) == 2 && target.isEmpty() && isHeadingForward(from, to);
    }

    private boolean isHeadingForward(Position from, Position to) {
        if (pieceColor.equals(PieceColor.WHITE)) {
            return from.isLowerTo(to) && from.columnGap(to) == 0;
        }
        return from.isUpperTo(to) && from.columnGap(to) == 0;
    }
}
