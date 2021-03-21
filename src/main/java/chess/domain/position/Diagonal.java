package chess.domain.position;

import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Diagonal {
    UP_RIGHT((source, target) ->
            !source.largeX(target) && !source.largeY(target),
            new int[]{1, 1}),
    UP_LEFT((source, target) ->
            source.largeX(target) && !source.largeY(target),
            new int[]{-1, 1}),
    DOWN_RIGHT((source, target) ->
            !source.largeX(target) && source.largeY(target),
            new int[]{1, -1}),
    DOWN_LEFT((source, target) ->
            source.largeX(target) && source.largeY(target),
            new int[]{-1, -1});

    private final BiPredicate<Position, Position> findDiagonal;
    private final int[] changeValues;

    Diagonal(BiPredicate<Position, Position> findDiagonal, int[] changeValues) {
        this.findDiagonal = findDiagonal;
        this.changeValues = changeValues;
    }

    public static Diagonal findDiagonalByTwoPosition(Position source, Position target) {
        return Arrays.stream(values()).filter(value -> value.findDiagonal.test(source, target))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("[ERROR] 올바른 대각선 방향이 아닙니다."));
    }

    public void hasPieceInPath(Position source, Position target, Pieces pieces) {
        int xSum = changeValues[0];
        int ySum = changeValues[1];
        int sourceX = source.getX();
        int sourceY = source.getY();
        int count = Math.abs(source.xDistance(target));
        for (int i = 1; i < count; i++) {
            for (int j = 1; j < count; j++) {
                Piece piece = pieces.findByPosition(
                        Position.of((char) (sourceX + (xSum * i)), (char) (sourceY + (ySum * j))));
                checkAbleToJump(piece);
            }
        }
    }

    private void checkAbleToJump(Piece piece) {
        if (!(piece.isEmpty())) {
            throw new IllegalArgumentException("[ERROR] 기물을 뛰어 넘어 이동할 수 없습니다.");
        }
    }
}
