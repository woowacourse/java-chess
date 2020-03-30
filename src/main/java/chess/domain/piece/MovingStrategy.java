package chess.domain.piece;

import chess.domain.board.Path;

@FunctionalInterface
public interface MovingStrategy {
    boolean test(Path path);

    static boolean king(Path path) {
        return path.distanceSquare() <= 2
            && (path.isEndEmpty() || path.isEnemyOnEnd());
    }

    static boolean queen(Path path) {
        return rook(path) || bishop(path);
    }

    static boolean rook(Path path) {
        return path.isStraight()
            && (path.isEndEmpty() || path.isEnemyOnEnd())
            && !path.isBlocked();
    }

    static boolean bishop(Path path) {
        return path.isDiagonal()
            && (path.isEndEmpty() || path.isEnemyOnEnd())
            && !path.isBlocked();
    }

    static boolean knight(Path path) {
        return path.distanceSquare() == 5
            && (path.isEndEmpty() || path.isEnemyOnEnd());
    }

    static boolean pawn(Path path) {
        if (path.isOnInitialPosition()) {
            return (path.distanceSquare() == 4 || path.distanceSquare() == 1)
                && path.headingForward()
                && path.isEndEmpty();
        }
        if (path.isEnemyOnEnd()) {
            return path.distanceSquare() == 2
                && path.headingForward();
        }
        return path.distanceSquare() == 1
            && path.headingForward() && path.isEndEmpty();
    }
}
