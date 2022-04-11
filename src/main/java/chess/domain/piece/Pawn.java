package chess.domain.piece;

import static chess.domain.piece.Team.BLACK;
import static chess.domain.piece.Team.WHITE;

import chess.domain.board.position.Position;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;

public class Pawn extends PromotablePiece {

    private static final String NAME = "Pawn";
    private static final String SYMBOL = "p";
    private static final double SCORE = 1;

    private static final Map<Team, Integer> DIRECTION_VALUE_BY_TEAM = Map.of(BLACK, 1, WHITE, -1);

    private boolean isFirstMove = true;

    public Pawn(final Team team) {
        super(team);
    }

    @Override
    public boolean canMove(final Position sourcePosition,
                           final Position targetPosition,
                           final List<Position> otherPositions) {
        if (isFirstMove) {
            return canMoveFirst(sourcePosition, targetPosition, otherPositions) ||
                    canAttackMove(sourcePosition, targetPosition, otherPositions);
        }
        return canMoveNotFirst(sourcePosition, targetPosition, otherPositions) ||
                canAttackMove(sourcePosition, targetPosition, otherPositions);
    }

    private boolean canMoveFirst(final Position sourcePosition,
                                 final Position targetPosition,
                                 final List<Position> otherPositions) {
        final int directionValue = DIRECTION_VALUE_BY_TEAM.get(team);
        final BiPredicate<Integer, Integer> firstMoveCondition = (fileMove, rankMove) ->
                fileMove == 0 && (rankMove == 1 * directionValue || rankMove == 2 * directionValue);
        isFirstMove = false;

        return sourcePosition.canMove(targetPosition, firstMoveCondition) &&
                !sourcePosition.isOtherPieceInPathToTarget(targetPosition, otherPositions) &&
                !isEnemyInTargetPosition(targetPosition, otherPositions);
    }

    private boolean canMoveNotFirst(final Position sourcePosition,
                                    final Position targetPosition,
                                    final List<Position> otherPositions) {
        final int directionValue = DIRECTION_VALUE_BY_TEAM.get(team);
        final BiPredicate<Integer, Integer> moveCondition = (fileMove, rankMove) ->
                fileMove == 0 && rankMove == 1 * directionValue;

        return sourcePosition.canMove(targetPosition, moveCondition) &&
                !isEnemyInTargetPosition(targetPosition, otherPositions);
    }

    private boolean canAttackMove(final Position sourcePosition,
                                  final Position targetPosition,
                                  final List<Position> otherPositions) {
        final int directionValue = DIRECTION_VALUE_BY_TEAM.get(team);
        final BiPredicate<Integer, Integer> attackMoveCondition = (fileMove, rankMove) ->
                (fileMove == 1 || fileMove == -1) && rankMove == 1 * directionValue;
        return sourcePosition.canMove(targetPosition, attackMoveCondition) &&
                isEnemyInTargetPosition(targetPosition, otherPositions);
    }

    private boolean isEnemyInTargetPosition(final Position targetPosition, final List<Position> otherPositions) {
        return otherPositions.stream()
                .anyMatch(otherPosition -> targetPosition == otherPosition);
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public String getSymbol() {
        if (team.isBlack()) {
            return SYMBOL.toUpperCase();
        }
        return SYMBOL;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
