package chess.domain.piece;

import chess.exception.NoSuchPermittedChessPieceException;

import java.util.*;
import java.util.function.Predicate;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Pieces {
    private static final int COUNT_OF_KING = 2;
    private static final double PAWN_EXCEPTED_CONDITION_RATIO = 0.5;

    private final List<Piece> pieces;

    public Pieces(final List<Piece> pieces) {
        this.pieces = new ArrayList<>(pieces);
    }

    public Piece findControllablePieceByPosition(final Color color, final Position source) {
        return pieces.stream()
                .filter(piece -> piece.isSamePosition(source))
                .filter(piece -> piece.isSameColor(color))
                .findAny()
                .orElseThrow(NoSuchPermittedChessPieceException::new);
    }

    public void catchPiece(final Color color) {
        Set<Map.Entry<Position, Long>> duplicatePosition = findDuplicatePosition();
        removeIfExistDuplicatePositionByColor(color, duplicatePosition);
    }

    private Set<Map.Entry<Position, Long>> findDuplicatePosition() {
        return pieces.stream()
                .collect(groupingBy(Piece::getPosition, counting()))
                .entrySet();
    }

    private void removeIfExistDuplicatePositionByColor(final Color color,
                                                       final Set<Map.Entry<Position, Long>> duplicatePosition) {
        duplicatePosition.stream()
                .filter(entry -> entry.getValue() == 2)
                .map(Map.Entry::getKey)
                .findAny()
                .ifPresent(position -> pieces.removeIf(duplicateCondition(color, position)));
    }

    private Predicate<Piece> duplicateCondition(final Color color, final Position position) {
        return piece -> piece.isSamePosition(position) &&
                !piece.isSameColor(color);
    }

    public boolean isCaughtKing() {
        long countOfKingOnBoard = pieces.stream()
                .filter(Piece::isKing)
                .count();

        return countOfKingOnBoard != COUNT_OF_KING;
    }

    public List<Piece> getPieces() {
        return Collections.unmodifiableList(pieces);
    }

    public double getBlackScore() {
        double totalScore = getTotalScore(Color.BLACK);
        long countOfExpectedPawn = getCountOfExpectedPawn(Color.BLACK);

        return totalScore - PAWN_EXCEPTED_CONDITION_RATIO * countOfExpectedPawn;
    }

    public double getWhiteScore() {
        double totalScore = getTotalScore(Color.WHITE);
        long countOfExpectedPawn = getCountOfExpectedPawn(Color.WHITE);

        return totalScore - PAWN_EXCEPTED_CONDITION_RATIO * countOfExpectedPawn;
    }

    private double getTotalScore(Color color) {
        return pieces.stream()
                .filter(piece -> piece.isSameColor(color))
                .mapToDouble(Piece::getScore)
                .sum();
    }

    private long getCountOfExpectedPawn(final Color color) {
        return pieces.stream()
                .filter(piece -> piece.isSameColor(color))
                .filter(Piece::isPawn)
                .collect(groupingBy(Piece::getColumn, counting()))
                .entrySet().stream()
                .map(Map.Entry::getValue)
                .filter(countOfExpectedPawn -> countOfExpectedPawn >= 2)
                .mapToInt(Long::intValue)
                .sum();
    }

    public boolean isNoneMatchByFilteredPieces(Predicate<Piece> filteredCondition, Predicate<Piece> noneMatchedCondition) {
        return pieces.stream()
                .filter(filteredCondition)
                .noneMatch(noneMatchedCondition);
    }

    public boolean isEnemyExist(Position target, Color color) {
        return pieces.stream()
                .anyMatch(
                        piece -> piece.isSamePosition(target) && piece.isSameColor(color)
                );
    }

}
