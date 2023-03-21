package chess.domain.pieces;

import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.domain.strategy.PawnDirection;
import chess.domain.strategy.Route;
import java.util.List;

public class WhitePawn extends Piece {

    private static final List<PawnDirection> WHITE_PAWN_FIRST_MOVE_DIRECTION = List.of(
        PawnDirection.WHITE_PAWN_MOVE,
        PawnDirection.WHITE_PAWN_DOUBLE_MOVE,
        PawnDirection.WHITE_PAWN_RIGHT_DIAGONAL_ATTACK,
        PawnDirection.WHITE_PAWN_LEFT_DIAGONAL_ATTACK
    );

    private static final List<PawnDirection> WHITE_PAWN_MOVE_DIRECTION = List.of(
        PawnDirection.WHITE_PAWN_MOVE,
        PawnDirection.WHITE_PAWN_RIGHT_DIAGONAL_ATTACK,
        PawnDirection.WHITE_PAWN_LEFT_DIAGONAL_ATTACK
    );

    private static final Row INIT_ROW_POSITION = Row.TWO;

    public WhitePawn(final Team team) {
        super(team);
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
        if (direction == PawnDirection.WHITE_PAWN_DOUBLE_MOVE) {
            return Route.generateRouteFromWhitePawnDoubleMove(direction, source, destination);
        }
        return Route.generateRouteFromPawn(direction, source, destination);
    }

    private boolean isFirstMove(final Position source) {
        return source.isSameRow(INIT_ROW_POSITION);
    }

    private void validateFirstMoveDirection(final Position source, final Position destination, final boolean isAttack) {
        WHITE_PAWN_FIRST_MOVE_DIRECTION.stream()
            .filter(vector -> vector.isSameDirection(source, destination) && vector.isAttackMove(isAttack))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("White Pawn의 첫 움직임으로 올바르지 않습니다."));
    }

    private void validateAfterFirstMoveDirection(final Position source, final Position destination, final boolean isAttack) {
        WHITE_PAWN_MOVE_DIRECTION.stream()
            .filter(vector -> vector.isSameDirection(source, destination) && vector.isAttackMove(isAttack))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("White Pawn의 움직임으로 올바르지 않습니다."));
    }

    private PawnDirection findDirection(final Position source, final Position destination) {
        if (isFirstMove(source)) {
            return WHITE_PAWN_FIRST_MOVE_DIRECTION.stream()
                .filter(vector -> vector.isSameDirection(source, destination))
                .findFirst()
                .get();
        }
        return WHITE_PAWN_MOVE_DIRECTION.stream()
            .filter(vector -> vector.isSameDirection(source, destination))
            .findFirst()
            .get();
    }
}
