package chess.domains.piece;

import chess.domains.position.Position;

public class Pawn extends Piece {
    public Pawn(PieceColor pieceColor) {
        super(pieceColor, PieceType.PAWN.name, PieceType.PAWN.score);
    }

    @Override
    public boolean isValidMove(Position current, Position target) {
        if (isInitialPosition(current)) {
            return canFirstMove(current, target);
        }

        int xGap = current.xGapBetween(target);
        int yGap = current.yGapBetween(target);

        if (pieceColor == PieceColor.WHITE) {
            return Math.abs(xGap) <= 1 && yGap == 1;
        }
        return Math.abs(xGap) <= 1 && yGap == -1;
    }

    private boolean isInitialPosition(Position current) {
        return (pieceColor == PieceColor.WHITE && current.isRow(2))
                || (pieceColor == PieceColor.BLACK && current.isRow(7));
    }

    private boolean canFirstMove(Position current, Position target) {
        int xGap = current.xGapBetween(target);
        int yGap = current.yGapBetween(target);

        if (pieceColor == PieceColor.WHITE) {
            return Math.abs(xGap) <= 1 && yGap > 0 && yGap <= 2;
        }
        return Math.abs(xGap) <= 1 && yGap >= -2 && yGap < 0;
    }

}
