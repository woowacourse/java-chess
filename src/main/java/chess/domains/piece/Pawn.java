package chess.domains.piece;

import chess.domains.position.Position;

public class Pawn extends Piece {
    public Pawn(PieceColor pieceColor) {
        super(pieceColor, "p");
    }

    @Override
    public boolean isValidMove(Position current, Position target) {
        if (isInitialPosition(current)) {
            return canFirstMove(current, target);
        }

        if (pieceColor == PieceColor.WHITE) {
            return current.xGapBetween(target) == 0 && current.yGapBetween(target) == 1;
        }
        return current.xGapBetween(target) == 0 && current.yGapBetween(target) == -1;
    }

    private boolean isInitialPosition(Position current) {
        return (pieceColor == PieceColor.WHITE && current.isRow(2))
                || (pieceColor == PieceColor.BLACK && current.isRow(7));
    }

    private boolean canFirstMove(Position current, Position target) {
        if (pieceColor == PieceColor.WHITE) {
            return current.xGapBetween(target) == 0 && current.yGapBetween(target) <= 2 && current.yGapBetween(target) > 0;
        }
        return current.xGapBetween(target) == 0 && current.yGapBetween(target) >= -2 && current.yGapBetween(target) < 0;
    }
}
