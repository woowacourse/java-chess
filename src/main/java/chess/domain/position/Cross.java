package chess.domain.position;

import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Cross {
    UP((source, target) ->
            source.sameX(target.getX()) && !source.largeY(target)) {
        @Override
        public void hasPieceInPath(Position source, Position target, Pieces pieces) {
            int count = Math.abs(source.yDistance(target));
            for (int i = 1; i < count; i++) {
                Piece piece = pieces.findByPosition(
                        source.movedPositionByNumber(0, i));
                checkAbleToJump(piece);
            }
        }
    },
    DOWN((source, target) ->
            source.sameX(target.getX()) && source.largeY(target)) {
        @Override
        public void hasPieceInPath(Position source, Position target, Pieces pieces) {
            int count = Math.abs(source.yDistance(target));
            for (int i = 1; i < count; i++) {
                Piece piece = pieces.findByPosition(
                        source.movedPositionByNumber(0, -i));
                checkAbleToJump(piece);
            }
        }
    },
    RIGHT((source, target) ->
            !source.largeX(target) && source.sameY(target.getY())) {
        @Override
        public void hasPieceInPath(Position source, Position target, Pieces pieces) {
            int count = Math.abs(source.xDistance(target));
            for (int i = 1; i < count; i++) {
                Piece piece = pieces.findByPosition(
                        source.movedPositionByNumber(i, 0));
                checkAbleToJump(piece);
            }
        }
    },
    LEFT((source, target) ->
            source.largeX(target) && source.sameY(target.getY())) {
        @Override
        public void hasPieceInPath(Position source, Position target, Pieces pieces) {
            int count = Math.abs(source.xDistance(target));
            for (int i = 1; i < count; i++) {
                Piece piece = pieces.findByPosition(
                        source.movedPositionByNumber(-i, 0));
                checkAbleToJump(piece);
            }
        }
    };

    private final BiPredicate<Position, Position> findCross;

    Cross(BiPredicate<Position, Position> findCross) {
        this.findCross = findCross;
    }

    public static Cross findCrossByTwoPosition(Position source, Position target) {
        return Arrays.stream(values())
                .filter(value -> value.findCross.test(source, target))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("[ERROR] 올바른 십자 방향이 아닙니다."));
    }

    public abstract void hasPieceInPath(Position source, Position target, Pieces pieces);

    void checkAbleToJump(Piece piece) {
        if (!(piece.isEmpty())) {
            throw new IllegalArgumentException("[ERROR] 기물을 뛰어 넘어 이동할 수 없습니다.");
        }
    }
}
