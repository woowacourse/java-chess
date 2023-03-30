package chess.model.domain.piece;

import chess.model.domain.position.Movement;
import chess.model.domain.position.Position;
import chess.model.domain.position.Rank;
import chess.model.exception.CantMoveFromToException;
import java.util.List;
import java.util.Map;

public class Pawn extends Piece {

    static final String CANT_MOVE_TO_MESSAGE = "움직일 수 없는 위치입니다.";
    private static final Map<Color, Movement> CAN_MOVE_EMPTY_DESTINATION = Map.of(
            Color.BLACK, Movement.D,
            Color.WHITE, Movement.U
    );
    private static final Map<Color, List<Movement>> CAN_MOVE_ENEMY_DESTINATION = Map.of(
            Color.BLACK, List.of(Movement.DR, Movement.DL),
            Color.WHITE, List.of(Movement.UR, Movement.UL)
    );
    private static final Map<Color, Rank> CAN_MOVE_TWO_BLOCK_RANK = Map.of(
            Color.WHITE, Rank.TWO,
            Color.BLACK, Rank.SEVEN
    );

    public Pawn(final Color color) {
        super(color, PieceType.PAWN);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Movement searchMovement(final Position from, final Position to, final Piece destination) {
        final Movement movement = to.convertMovement(from);
        if (canMoveFront(destination, movement)) {
            return searchPathIfDestinationEmpty(from, to, movement);
        }
        if (canMoveDiagonal(destination, movement)) {
            return movement;
        }
        throw new CantMoveFromToException();
    }

    private boolean canMoveFront(final Piece destination, final Movement movement) {
        return destination.isEmpty() && movement == CAN_MOVE_EMPTY_DESTINATION.get(color);
    }

    private Movement searchPathIfDestinationEmpty(final Position from, final Position to, final Movement movement) {
        if (checkMove1Square(from, to) || checkMove2Square(from, to)) {
            return movement;
        }
        throw new CantMoveFromToException();
    }

    private boolean checkMove1Square(final Position from, final Position to) {
        return to.rankDifference(from) == 1;
    }

    private boolean checkMove2Square(final Position from, final Position to) {
        return from.isEqualRank(CAN_MOVE_TWO_BLOCK_RANK.get(color)) && to.rankDifference(from) == 2;
    }

    private boolean canMoveDiagonal(final Piece destination, final Movement movement) {
        return !destination.isEmpty() && CAN_MOVE_ENEMY_DESTINATION.get(color).contains(movement);
    }
}
