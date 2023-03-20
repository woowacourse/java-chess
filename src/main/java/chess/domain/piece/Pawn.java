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
    
    private static final int WHITE_PAWN_START_RANK = 2;
    private static final int BLACK_PAWN_START_RANK = 7;
    private static final int MAXIMUM_WHITE_PAWN_MANHATTAN_DISTANCE = 2;
    private static final int MAXIMUM_BLACK_PAWN_MANHATTAN_DISTANCE = -2;

    public Pawn(final Color color) {
        super(color);
    }

    public Path searchPathTo(Position from, Position to, Piece locatedPiece) {
        Movement movement = to.convertMovement(from);

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

        // 상대 말인 경우
        if (canMoveDiagonal(locatedPiece, movement)) {
            return new Path();
        }
        throw new IllegalStateException(this.getClass().getSimpleName() + "이(가) 이동할 수 없는 경로입니다.");
    }

    @Override
    protected Path moveToLocatedPiece(final Position from, final Position to, final Movement movement) {
        return null;
    }

    @Override
    protected boolean canNotMoveToLocatedPiece(final Movement movement) {
        return false;
    }

    private boolean canMove(final Piece destination, final Movement movement) {
        return destination == null && movement == CAN_MOVE_EMPTY_DESTINATION.get(color);
    }

    private boolean canMoveDiagonal(final Piece destination, final Movement movement) {
        return destination != null && destination.color.isDifferentColor(color)
                && CAN_MOVE_ENEMY_DESTINATION.get(color).contains(movement);
    }

    private boolean canMoveBlackPawn(final Position from, final Position to) {
        return color.isBlack() && from.rank().value() == BLACK_PAWN_START_RANK
                && rankDifference(from, to) == MAXIMUM_BLACK_PAWN_MANHATTAN_DISTANCE;
    }

    private boolean canMoveWhitePawn(final Position from, final Position to) {
        return color.isWhite() && from.rank().value() == WHITE_PAWN_START_RANK
                && rankDifference(from, to) == MAXIMUM_WHITE_PAWN_MANHATTAN_DISTANCE;
    }

    private int rankDifference(final Position from, final Position to) {
        return to.calculateRankBetween(from);
    }
}
