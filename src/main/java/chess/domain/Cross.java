package chess.domain;

import chess.domain.piece.Pieces;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Cross {
    UP((source, target) ->
            source.xDistance(target) == 0 && !source.largeY(target),
            new int[]{0, 1}),
    DOWN((source, target) ->
            source.xDistance(target) == 0 && source.largeY(target),
            new int[]{0, -1}),
    RIGHT((source, target) ->
            !source.largeX(target) && source.yDistance(target) == 0,
            new int[]{1, 0}),
    LEFT((source, target) ->
            source.largeX(target) && source.yDistance(target) == 0,
            new int[]{-1, 0});

    private final BiPredicate<Position, Position> findCross;
    private final int[] changeValues;

    Cross(BiPredicate<Position, Position> findCross, int[] changeValues) {
        this.findCross = findCross;
        this.changeValues = changeValues;
    }

    public static Cross findCrossByTwoPosition(Position source, Position target) {
        return Arrays.stream(values()).filter(value -> value.findCross.test(source, target))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 올바른 십자 방향이 아닙니다."));
    }

    public void hasPieceInPath(Position source, Position target, Pieces pieces) {
        int xSum = changeValues[0];
        int ySum = changeValues[1];
        int sourceX = source.getX();
        int sourceY = source.getY();
        if (this == UP || this == DOWN) {
            int count = Math.abs(source.xDistance(target));
            for (int i = 1; i < count; i++) {
                Piece piece = pieces.findByPosition(
                        Position.of((char) (sourceX + (xSum * i)), (char) (sourceY)));
                if (!(piece instanceof Empty)) {
                    throw new IllegalArgumentException("[ERROR] 기물을 뛰어 넘어 이동할 수 없습니다.");
                }
            }
        }

        if (this == RIGHT || this == LEFT) {
            int count = Math.abs(source.yDistance(target));
            for (int i = 1; i < count; i++) {
                Piece piece = pieces.findByPosition(
                        Position.of((char) (sourceX), (char) (sourceY + (ySum * i))));
                if (!(piece instanceof Empty)) {
                    throw new IllegalArgumentException("[ERROR] 기물을 뛰어 넘어 이동할 수 없습니다.");
                }
            }
        }
    }
}
