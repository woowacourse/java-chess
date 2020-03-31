package chess.domains.piece;

import chess.domains.position.Direction;
import chess.domains.position.Position;
import chess.domains.position.Row;

public class Pawn extends Piece {
    private static final String INVALID_DIAGONAL_MOVE_OF_PAWN_ERR_MSG = "폰은 상대말을 잡는 경우 이 외에 대각선으로 이동할 수 없습니다.";
    private static final String INVALID_VERTICAL_MOVE_OF_PAWN_ERR_MSG = "폰은 앞에 있는 상대를 잡을 수 없습니다.";

    public Pawn(PieceColor pieceColor) {
        super(pieceColor, PieceType.PAWN);
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
        return (pieceColor == PieceColor.WHITE && current.isRow(Row.TWO))
                || (pieceColor == PieceColor.BLACK && current.isRow(Row.SEVEN));
    }

    private boolean canFirstMove(Position current, Position target) {
        int xGap = current.xGapBetween(target);
        int yGap = current.yGapBetween(target);

        if (pieceColor == PieceColor.WHITE) {
            return Math.abs(xGap) <= ONE_BLOCK && NO_MOVE < yGap && yGap <= TWO_BLOCKS_UP;
        }
        return Math.abs(xGap) <= ONE_BLOCK && TWO_BLOCKS_DOWN <= yGap && yGap < NO_MOVE;
    }

    public void validPawnMove(Piece targetPiece, Position source, Position target) {
        Direction direction = Direction.findDirection(source, target);

        if (direction.isDiagonal() && targetPiece.is(PieceType.BLANK)) {
            throw new IllegalArgumentException(INVALID_DIAGONAL_MOVE_OF_PAWN_ERR_MSG);
        }

        if (direction.isVertical() && !targetPiece.is(PieceType.BLANK)) {
            throw new IllegalArgumentException(INVALID_VERTICAL_MOVE_OF_PAWN_ERR_MSG);
        }
    }
}
