package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import domain.piece.Piece;
import domain.piece.PieceType;

public class ScoreCalculator {
    public static final double PAWN_LOWER_VALUE = 0.5;
    public static final int STANDARD_PAWN_COUNT = 1;

    public double sumBlackScore(Map<Square, Piece> pieceLocations) {
        return sumScoreWithoutPawn(pieceLocations, Piece::isBlack)
                + sumPawnScore(pieceLocations, Piece::isBlack);
    }

    private double sumScoreWithoutPawn(Map<Square, Piece> pieceLocations, Predicate<Piece> predicate) {
        return pieceLocations.values().stream()
                .filter(piece -> piece.pieceType() != PieceType.PAWN)
                .filter(predicate)
                .mapToDouble(Piece::score)
                .sum();
    }

    private double sumPawnScore(Map<Square, Piece> pieceLocations, Predicate<Piece> predicate) {
        Set<Square> pawnSquares = getPawnSquares(pieceLocations, predicate);
        List<Integer> pawnCounts = getPawnCounts(pawnSquares);
        return pawnCounts.stream()
                .mapToDouble(this::calculatePawnScore)
                .sum();
    }

    public Set<Square> getPawnSquares(Map<Square, Piece> pieceLocations, Predicate<Piece> predicate) {
        Set<Square> pawnSquare = new HashSet<>();
        pieceLocations.entrySet().stream()
                .filter(s -> s.getValue().pieceType() == PieceType.PAWN)
                .filter(s -> predicate.test(s.getValue()))
                .map(s -> pawnSquare.add(s.getKey()))
                .count();
        return pawnSquare;
    }

    private List<Integer> getPawnCounts(Set<Square> pawnSquares) {
        List<Integer> pawnCounts = new ArrayList<>();
        for (ChessColumn chessColumn : ChessColumn.values()) {
            int pawnCount = getPawnCount(pawnSquares, chessColumn);
            pawnCounts.add(pawnCount);
        }
        return pawnCounts;
    }

    private double calculatePawnScore(int pawnCount) {
        if (pawnCount > STANDARD_PAWN_COUNT) {
            return pawnCount * PAWN_LOWER_VALUE;
        }
        return pawnCount * PieceType.PAWN.getValue();
    }

    private int getPawnCount(Set<Square> pawnSquares, ChessColumn chessColumn) {
        return (int) pawnSquares.stream()
                .filter(s -> s.isSameColumn(chessColumn))
                .count();
    }

    public double sumWhiteScore(Map<Square, Piece> pieceLocations) {
        return sumScoreWithoutPawn(pieceLocations, Piece::isWhite)
                + sumPawnScore(pieceLocations, Piece::isWhite);
    }

}
