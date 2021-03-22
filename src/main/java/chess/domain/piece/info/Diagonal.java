package chess.domain.piece.info;

import chess.domain.piece.CurrentPieces;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Diagonal {
    UP_RIGHT((source, target) ->
            !source.isSubtractXPositive(target) && !source.isSubtractYPositive(target),
            new int[]{1, 1}),
    UP_LEFT((source, target) ->
            source.isSubtractXPositive(target) && !source.isSubtractYPositive(target),
            new int[]{-1, 1}),
    DOWN_RIGHT((source, target) ->
            !source.isSubtractXPositive(target) && source.isSubtractYPositive(target),
            new int[]{1, -1}),
    DOWN_LEFT((source, target) ->
            source.isSubtractXPositive(target) && source.isSubtractYPositive(target),
            new int[]{-1, -1});

    private final BiPredicate<Position, Position> findDiagonal;
    private final int[] changeValues;

    Diagonal(BiPredicate<Position, Position> findDiagonal, int[] changeValues) {
        this.findDiagonal = findDiagonal;
        this.changeValues = changeValues;
    }

    public static Diagonal findDiagonalByTwoPosition(Position source, Position target) {
        return Arrays.stream(values())
                .filter(value -> source.isDiagonal(target))
                .filter(value -> value.findDiagonal.test(source, target))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 올바른 방향이 아닙니다."));
    }

    public void hasPieceInPath(Position source, Position target, CurrentPieces currentPieces) {
        int sourceX = source.getX();
        int sourceY = source.getY();
        int count = Math.abs(source.subtractX(target));
        for (int i = 1; i < count; i++) {
            validatePieceInPath(currentPieces, sourceX, sourceY, i);
        }
    }

    private void validatePieceInPath(CurrentPieces currentPieces, int sourceX, int sourceY, int count) {
        if (currentPieces.hasSamePositionPiece(
                Position.of((char) (sourceX + (changeValues[0] * count)), (char) (sourceY + (changeValues[1] * count))))) {
            throw new IllegalArgumentException("[ERROR] 기물을 뛰어 넘어 이동할 수 없습니다.");
        }
    }
}
