package chess.domain.pieces;

import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.domain.direction.PawnDirection;
import chess.domain.direction.Route;
import java.util.List;

public class BlackPawn extends Piece {

    private static final List<PawnDirection> BLACK_PAWN_FIRST_MOVE_DIRECTION = List.of(
        PawnDirection.BLACK_PAWN_MOVE,
        PawnDirection.BLACK_PAWN_DOUBLE_MOVE,
        PawnDirection.BLACK_PAWN_RIGHT_DIAGONAL_ATTACK,
        PawnDirection.BLACK_PAWN_LEFT_DIAGONAL_ATTACK
    );

    private static final List<PawnDirection> BLACK_PAWN_MOVE_DIRECTION = List.of(
        PawnDirection.BLACK_PAWN_MOVE,
        PawnDirection.BLACK_PAWN_RIGHT_DIAGONAL_ATTACK,
        PawnDirection.BLACK_PAWN_LEFT_DIAGONAL_ATTACK
    );

    private static final Row INIT_ROW_POSITION = Row.SEVEN;

    public BlackPawn(Team team) {
        super(team, PieceType.PAWN);
    }

    @Override
    public void canMove(final Position source, final Position destination, final boolean isAttack) {
        if (isFirstMove(source)) {
            validateFirstMoveDirection(source, destination, isAttack);
        }
        if (!isFirstMove(source)) {
            validateAfterFirstMoveDirection(source, destination, isAttack);
        }
    }

    @Override
    public Route generateRoute(final Position source, final Position destination) {
        PawnDirection direction = findDirection(source, destination);
        if (direction == PawnDirection.BLACK_PAWN_DOUBLE_MOVE) {
            return Route.generateRouteFromBlackPawnDoubleMove(direction, source, destination);
        }
        return Route.generateRouteFromPawn(direction, source, destination);
    }

    private boolean isFirstMove(final Position source) {
        return source.isSameRow(INIT_ROW_POSITION);
    }

    private void validateFirstMoveDirection(final Position source, final Position destination, final boolean isAttack) {
        BLACK_PAWN_FIRST_MOVE_DIRECTION.stream()
            .filter(vector -> vector.isSameDirection(source, destination) && vector.isAttackMove(isAttack))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Black Pawn의 첫 움직임으로 올바르지 않습니다."));
    }

    private void validateAfterFirstMoveDirection(final Position source, final Position destination, final boolean isAttack) {
        BLACK_PAWN_MOVE_DIRECTION.stream()
            .filter(vector -> vector.isSameDirection(source, destination) && vector.isAttackMove(isAttack))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Black Pawn의 움직임으로 올바르지 않습니다."));
    }

    private PawnDirection findDirection(final Position source, final Position destination) {
        if (isFirstMove(source)) {
            return BLACK_PAWN_FIRST_MOVE_DIRECTION.stream()
                .filter(vector -> vector.isSameDirection(source, destination))
                .findFirst()
                .get();
        }
        return BLACK_PAWN_MOVE_DIRECTION.stream()
            .filter(vector -> vector.isSameDirection(source, destination))
            .findFirst()
            .get();
    }
}
