package chess.domain;

import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {

    private static final int START_INCLUSIVE = 0;
    private static final int END_INCLUSIVE = 7;
    private static final double ANOTHER_PAWN_SCORE = 0.5;

    private final List<List<Piece>> value;

    public Board(List<List<Piece>> value) {
        this.value = new ArrayList<>(value);
    }

    public void shift(Position source, Position target) {
        Piece sourcePiece = findPiece(source);
        value.get(source.getRankIndex()).set(source.getFileIndex(), new EmptyPiece());
        value.get(target.getRankIndex()).set(target.getFileIndex(), sourcePiece);
    }

    public Piece findPiece(Position source) {
        return value.get(source.getRankIndex()).get(source.getFileIndex());
    }

    public double calculateScore(Color color) {
        double score = value.stream()
                .flatMap(List::stream)
                .filter(piece -> piece.isSameColor(color))
                .mapToDouble(Piece::getScore)
                .sum();

        return score - getPawnScore(color);
    }

    private double getPawnScore(Color color) {
        double pawnScore = 0;
        for (int i = START_INCLUSIVE; i <= END_INCLUSIVE; i++) {
            int pawnCount = getPawnCount(color, i);
            if (getPawnCount(color, i) > 1) {
                pawnScore += pawnCount * ANOTHER_PAWN_SCORE;
            }
        }
        return pawnScore;
    }

    private int getPawnCount(Color color, int fileIndex) {
        int pawnCount = 0;
        for (int j = START_INCLUSIVE; j <= END_INCLUSIVE; j++) {
            Piece piece = value.get(j).get(fileIndex);
            pawnCount += calculatePawnCount(piece, color);
        }

        return pawnCount;
    }

    private int calculatePawnCount(Piece piece, Color color) {
        if (piece.isSamePieceType(PieceType.PAWN) && piece.isSameColor(color)) {
            return 1;
        }

        return 0;
    }

    public long calculateKingCount() {
        return value.stream()
                .flatMap(List::stream)
                .filter(piece -> piece.isSamePieceType(PieceType.KING))
                .count();
    }

    public List<List<Piece>> getValue() {
        return Collections.unmodifiableList(value);
    }
}
