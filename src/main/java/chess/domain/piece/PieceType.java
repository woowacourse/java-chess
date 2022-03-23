package chess.domain.piece;

import chess.domain.position.Position;
import java.util.function.BiPredicate;

public enum PieceType {
    PAWN(PieceType::getPawnStrategy),
    ROOK(PieceType::getRookStrategy),
    KNIGHT(PieceType::getKnightStrategy),
    BISHOP(PieceType::getBishopStrategy),
    QUEEN(PieceType::getQueenStrategy),
    KING(PieceType::getKingStrategy);

    private final BiPredicate<Position, Position> strategy;

    PieceType(BiPredicate<Position, Position> strategy) {
        this.strategy = strategy;
    }

    private static boolean getPawnStrategy(Position from, Position to) {
        if (from.isFarFromMoreThanOne(to)) {
            return false;
        }

        return from.isSameXAxis(to);
    }

    private static boolean getRookStrategy(Position from, Position to) {
        return from.isSameXAxis(to) || from.isSameYAxis(to);
    }

    private static boolean getKnightStrategy(Position from, Position to) {
        return from.isOnSevenShape(to);
    }

    private static boolean getBishopStrategy(Position from, Position to) {
        return from.isOnDiagonal(to);
    }

    private static boolean getQueenStrategy(Position from, Position to) {
        return from.isOnDiagonal(to) || from.isSameXAxis(to) || from.isSameYAxis(to);
    }

    private static boolean getKingStrategy(Position from, Position to) {
        if (from.isFarFromMoreThanOne(to)) {
            return false;
        }
        return from.isOnDiagonal(to) || from.isSameXAxis(to) || from.isSameYAxis(to);
    }

    public boolean isMovable(Position from, Position to) {
        return strategy.test(from, to);
    }
}
