package chess.domain;

import chess.domain.piece.CurrentPieces;
import chess.domain.piece.Position;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Diagonal {
    // + +
//    UP_RIGHT((source, target) ->
//            source.isSubtractXPositive(target) && source.isSubtractYPositive(target)),
//    UP_LEFT(),
//    DOWN_RIGHT(),
//    DOWN_LEFT();
//
//    private BiPredicate<Position, Position> hasPieceInPath;
//
//    Diagonal(BiPredicate<Position, Position> hasPieceInPath) {
//        this.hasPieceInPath = hasPieceInPath;
//    }
//
//    public static Diagonal findDiagonalByTwoPosition(Position source, Position target) {
//        Arrays.stream(values()).filter(value -> value.hasPieceInPath.test(source, target))
//                .findFirst()
//                .orElseThrow(IllegalArgumentException::new);
//    }
//
//    public boolean hasPieceInPath(CurrentPieces currentPieces) {
//        this.
//
//    }
}
