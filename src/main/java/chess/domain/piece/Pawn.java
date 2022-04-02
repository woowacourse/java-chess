package chess.domain.piece;

import static chess.domain.piece.TeamColor.BLACK;
import static chess.domain.piece.TeamColor.WHITE;

import chess.domain.board.position.Position;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

public class Pawn extends PromotablePiece {

    private static final String SYMBOL = "p";
    private static final double SCORE = 1;

    private static final Map<TeamColor, Integer> DIRECTION_VALUE_BY_TEAM = Map.of(BLACK, 1, WHITE, -1);

    private boolean isFirstMove = true;

    public Pawn(final TeamColor teamColor, final Position position) {
        super(teamColor, position);
    }

    private Pawn(final TeamColor teamColor, final Position position, boolean isFirstMove) {
        this(teamColor, position);
        this.isFirstMove = isFirstMove;
    }

    @Override
    public Piece move(final List<Piece> otherPieces, final Position targetPosition) {
        try {
            validateMove(targetPosition, otherPieces);
        } catch (IllegalArgumentException exception) {
            validateCatch(targetPosition, otherPieces);
        }
        return new Pawn(teamColor, targetPosition, isFirstMove);
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    private void validateMove(final Position targetPosition, final List<Piece> otherPieces) {
        final int directionValue = DIRECTION_VALUE_BY_TEAM.get(teamColor);
        if (isFirstMove) {
            validateFirstMove(targetPosition, otherPieces, directionValue);
            isFirstMove = false;
            return;
        }
        final BiPredicate<Integer, Integer> moveCondition = (fileMove, rankMove) ->
                fileMove == 0 && rankMove == 1 * directionValue;
        position.validateTargetPosition(targetPosition, moveCondition);
        position.checkOtherPiecesInTarget(targetPosition, convertToPositions(otherPieces));
    }

    private void validateFirstMove(final Position targetPosition, final List<Piece> otherPieces,
                                   final int directionValue) {
        final BiPredicate<Integer, Integer> firstMoveCondition = (fileMove, rankMove) ->
                fileMove == 0 && (rankMove == 1 * directionValue || rankMove == 2 * directionValue);
        position.validateTargetPosition(targetPosition, firstMoveCondition);
        position.checkOtherPiecesInPathToTarget(targetPosition, convertToPositions(otherPieces));
        position.checkOtherPiecesInTarget(targetPosition, convertToPositions(otherPieces));
    }

    private void validateCatch(final Position targetPosition, final List<Piece> otherPieces) {
        validateEnemyInTargetPosition(targetPosition, otherPieces);

        final int directionValue = DIRECTION_VALUE_BY_TEAM.get(teamColor);
        final BiPredicate<Integer, Integer> catchInvalidCondition = (fileMove, rankMove) ->
                (fileMove == 1 || fileMove == -1) && rankMove == 1 * directionValue;
        position.validateTargetPosition(targetPosition, catchInvalidCondition);
    }

    private void validateEnemyInTargetPosition(final Position targetPosition, final List<Piece> otherPieces) {
        final List<Position> enemyPositions = otherPieces.stream()
                .filter(other -> other.teamColor != this.teamColor)
                .map(other -> other.position)
                .collect(Collectors.toUnmodifiableList());

        if (enemyPositions.stream()
                .noneMatch(enemyPosition -> enemyPosition == targetPosition)) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
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
        if (teamColor.isBlack()) {
            return SYMBOL.toUpperCase();
        }
        return SYMBOL;
    }
}
