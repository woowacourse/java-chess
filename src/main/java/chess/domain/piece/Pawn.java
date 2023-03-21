package chess.domain.piece;

import chess.domain.board.position.Movement;
import chess.domain.board.position.Path;
import chess.domain.board.position.Position;

import java.util.List;
import java.util.Map;

import static chess.domain.board.position.Movement.D;
import static chess.domain.board.position.Movement.DL;
import static chess.domain.board.position.Movement.DR;
import static chess.domain.board.position.Movement.U;
import static chess.domain.board.position.Movement.UL;
import static chess.domain.board.position.Movement.UR;

public class Pawn extends Piece {

    private static final Map<Color, Movement> CAN_MOVE_EMPTY_DESTINATION = Map.of(
            Color.BLACK, D,
            Color.WHITE, U
    );
    private static final Map<Color, List<Movement>> CAN_MOVE_ENEMY_DESTINATION = Map.of(
            Color.BLACK, List.of(DR, DL),
            Color.WHITE, List.of(UR, UL)
    );
    private static final List<Movement> MOVEMENT_CANDIDATES = List.of(D, U, DR, DL, UR, UL);

    private static final int WHITE_PAWN_START_ROW = 2;
    private static final int BLACK_PAWN_START_ROW = 7;
    private static final int MAXIMUM_WHITE_PAWN_MANHATTAN_DISTANCE = 2;
    private static final int MAXIMUM_BLACK_PAWN_MANHATTAN_DISTANCE = -2;

    public Pawn(final Color color) {
        super(color);
    }

    @Override
    protected Path moveToLocatedPiece(final Position from, final Position to,
                                      final Movement movement, final Piece locatedPiece) {
        if (canMove(locatedPiece, movement)) {

            if (canMoveWhitePawn(from, to)) {
                final Position wayPoint = from.moveBy(U);

                return new Path(wayPoint);
            }

            if (canMoveBlackPawn(from, to)) {
                Position wayPoint = from.moveBy(D);

                return new Path(wayPoint);
            }

            return new Path();
        }

        if (canMoveDiagonal(locatedPiece, movement)) {
            return new Path();
        }

        throw new IllegalStateException(getClass().getSimpleName() + "이(가) 이동할 수 없는 경로입니다.");
    }

    @Override
    protected boolean canNotMoveToLocatedPiece(final Movement movement) {
        return !MOVEMENT_CANDIDATES.contains(movement);
    }

    private boolean canMove(final Piece destination, final Movement movement) {
        return destination == null && movement == CAN_MOVE_EMPTY_DESTINATION.get(color);
    }

    private boolean canMoveDiagonal(final Piece destination, final Movement movement) {
        return destination != null && destination.isDifferentColor(this)
                && CAN_MOVE_ENEMY_DESTINATION.get(color).contains(movement);
    }

    private boolean canMoveBlackPawn(final Position from, final Position to) {
        return color.isBlack() && isBlackPawnStartRow(from) && hasBlackPawnMovedTwice(from, to);
    }

    private boolean isBlackPawnStartRow(final Position from) {
        return from.row().value() == BLACK_PAWN_START_ROW;
    }

    private boolean hasBlackPawnMovedTwice(final Position from, final Position to) {
        return rowDifference(from, to) == MAXIMUM_BLACK_PAWN_MANHATTAN_DISTANCE;
    }

    private int rowDifference(final Position from, final Position to) {
        return to.calculateRowBetween(from);
    }

    private boolean canMoveWhitePawn(final Position from, final Position to) {
        return color.isWhite() && isWhitePawnStartRow(from) && hasWhitePawnMovedTwice(from, to);
    }

    private boolean isWhitePawnStartRow(final Position from) {
        return from.row().value() == WHITE_PAWN_START_ROW;
    }

    private boolean hasWhitePawnMovedTwice(final Position from, final Position to) {
        return rowDifference(from, to) == MAXIMUM_WHITE_PAWN_MANHATTAN_DISTANCE;
    }
}
