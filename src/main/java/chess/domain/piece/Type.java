package chess.domain.piece;

import chess.domain.Camp;
import chess.domain.board.Position;
import java.util.function.BiPredicate;
import java.util.function.Function;

public enum Type {
    BISHOP(Position::inDiagonalWith, 3, Bishop::new),
    KING(Position::isNextTo, 0, King::new),
    KNIGHT(Type::canKnightApproach, 2.5, Knight::new),
    PAWN(Position::isNextTo, 1, Pawn::new),
    QUEEN((sourcePosition, targetPosition) -> {
        if (sourcePosition.inSameColumnWith(targetPosition)) {
            return true;
        }
        if (sourcePosition.inSameRowWith(targetPosition)) {
            return true;
        }
        return sourcePosition.inDiagonalWith(targetPosition);
    }, 9, Queen::new),
    ROOK((sourcePosition, targetPosition) -> {
        if (sourcePosition.inSameColumnWith(targetPosition)) {
            return true;
        }
        return sourcePosition.inSameRowWith(targetPosition);
    }, 5, Rook::new),
    NONE((sourcePosition, targetPosition) -> false, 0, (camp) -> new None());

    private static final int DISTANCE_KNIGHT_FIRST_STEP = 2;
    private static final int DISTANCE_KNIGHT_SECOND_STEP = 1;

    private final BiPredicate<Position, Position> distanceChecker;
    private final double score;
    private final Function<Camp, Piece> pieceSupplier;

    Type(BiPredicate<Position, Position> distanceChecker, double score, Function<Camp, Piece> pieceSupplier) {
        this.distanceChecker = distanceChecker;
        this.score = score;
        this.pieceSupplier = pieceSupplier;
    }

    private static boolean canKnightApproach(Position sourcePosition, Position targetPosition) {
        int columnDistance = sourcePosition.columnDistance(targetPosition);
        int rowDistance = sourcePosition.rowDistance(targetPosition);
        if (rowDistance == DISTANCE_KNIGHT_FIRST_STEP && columnDistance == DISTANCE_KNIGHT_SECOND_STEP) {
            return true;
        }
        return columnDistance == DISTANCE_KNIGHT_FIRST_STEP && rowDistance == DISTANCE_KNIGHT_SECOND_STEP;
    }

    public Piece generatePiece(Camp camp) {
        return this.pieceSupplier.apply(camp);
    }

    public boolean canNotApproach(Position sourcePosition, Position targetPosition) {
        return !this.distanceChecker.test(sourcePosition, targetPosition);
    }

    public double scoreOf(int count) {
        return this.score * count;
    }

    public double getScore() {
        return score;
    }

    @Override
    public String toString() {
        if (this == NONE) {
            return "";
        }
        return name().toLowerCase();
    }
}
