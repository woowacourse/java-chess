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
            return Math.abs(xGap) <= King.ONE_BLOCK && yGap == ONE_BLOCK_UP;
        }
        return Math.abs(xGap) <= King.ONE_BLOCK && yGap == ONE_BLOCK_DOWN;
    }

    private boolean isInitialPosition(Position current) {
        return (pieceColor == PieceColor.WHITE && current.isRow(INITIAL_ROW_OF_WHITE_PAWN))
                || (pieceColor == PieceColor.BLACK && current.isRow(INITIAL_ROW_OF_BLACK_PAWN));
    }

    private boolean canFirstMove(Position current, Position target) {
        int xGap = current.xGapBetween(target);
        int yGap = current.yGapBetween(target);

        if (pieceColor == PieceColor.WHITE) {
            return Math.abs(xGap) <= ONE_BLOCK && NO_MOVE < yGap && yGap <= TWO_BLOCKS_UP;
        }
        return Math.abs(xGap) <= ONE_BLOCK && TWO_BLOCKS_DOWN <= yGap && yGap < NO_MOVE;
    }

}
