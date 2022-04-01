package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.piece.notation.Color;
import chess.domain.piece.notation.ColorNotation;
import chess.domain.piece.notation.PieceNotation;
import chess.domain.position.Direction;
import chess.domain.position.Position;

import java.util.List;

public final class Pawn extends Piece {

    private static final int WHITE_INIT_RANK = 2;
    private static final int BLACK_INIT_RANK = 7;
    private static final int NO_PIECE_ON_ROUTE = 0;
    private static final int ONE_PIECE_ON_ROUTE = 1;

    public Pawn(final Color color) {
        super(new ColorNotation(color, PieceNotation.PAWN), PawnDirection(color));
    }

    private static List<Direction> PawnDirection(final Color color) {
        if (color.isWhite()) {
            return Direction.WHITE_PAWN_DIRECTION;
        }
        return Direction.BLACK_PAWN_DIRECTION;
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public void checkMoveRange(final Board board, final Position from, final Position to) {
        final var direction = Direction.of(from, to);
        if (directions.contains(direction) && canMove(board, from, to)) {
            return;
        }
        throw new IllegalArgumentException("폰을 해당 위치로 움직일 수 없습니다.");
    }

    private boolean canMove(final Board board, final Position from, final Position to) {
        final var direction = Direction.of(from, to);
        final var positions = direction.getPositions(from, to);
        if (positions.size() == NO_PIECE_ON_ROUTE) {
            return canMoveOneStep(board, to, direction);
        }
        if (positions.size() == ONE_PIECE_ON_ROUTE) {
            return canMoveTwoStep(board, from, to);
        }
        return false;
    }

    private boolean canMoveOneStep(final Board board, final Position to, final Direction direction) {
        if (board.hasPiece(to)) {
            return direction.isDiagonal();
        }
        return direction.isVertical();
    }

    private boolean canMoveTwoStep(final Board board, final Position from, final Position to) {
        final var direction = Direction.of(from, to);
        final var positions = direction.getPositions(from, to);

        return isFirstMove(from) && direction.isVertical() && !hasPieceInVerticalLine(board, to, positions);
    }

    private boolean isFirstMove(final Position from) {
        return from.getRankNumber() == getPawnInitRank();
    }

    private int getPawnInitRank() {
        if (notation.isSameColor(Color.WHITE)) {
            return WHITE_INIT_RANK;
        }
        return BLACK_INIT_RANK;
    }

    private boolean hasPieceInVerticalLine(final Board board, final Position to, final List<Position> positions) {
        return board.hasPiece(to) || board.hasPiece(positions.get(0));
    }
}
