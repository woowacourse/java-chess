package chess.domain.position;

import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Diagonal {
    UP_RIGHT((source, target) ->
            !source.largeX(target) && !source.largeY(target), 1, 1),
    UP_LEFT((source, target) ->
            source.largeX(target) && !source.largeY(target), -1, 1),
    DOWN_RIGHT((source, target) ->
            !source.largeX(target) && source.largeY(target), 1, -1),
    DOWN_LEFT((source, target) ->
            source.largeX(target) && source.largeY(target), -1, -1);

    private final BiPredicate<Position, Position> findDiagonal;
    private final int xChangeValue;
    private final int yChangeValue;

    Diagonal(BiPredicate<Position, Position> findDiagonal, int xChangeValue, int yChangeValue) {
        this.findDiagonal = findDiagonal;
        this.xChangeValue = xChangeValue;
        this.yChangeValue = yChangeValue;
    }

    public static Diagonal findDiagonalByTwoPosition(Position source, Position target) {
        return Arrays.stream(values())
                .filter(value -> source.isDiagonal(target))
                .filter(value -> value.findDiagonal.test(source, target))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("[ERROR] 올바른 대각선 방향이 아닙니다."));
    }

    public void hasPieceInPath(Position source, Position target, Pieces pieces) {
        int count = Math.abs(source.xDistance(target));
        for (int i = 1; i < count; i++) {
            for (int j = 1; j < count; j++) {
                Piece piece = pieces.findByPosition(
                        source.movedPositionByNumber(xChangeValue * i, yChangeValue * j));
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
