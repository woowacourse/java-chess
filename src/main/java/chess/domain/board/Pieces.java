package chess.domain.board;

import chess.domain.Color;
import chess.domain.File;
import chess.domain.Position;
import chess.domain.piece.BlankPiece;
import chess.domain.piece.Piece;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Pieces {
    private static final int INITIAL_KING_COUNT = 2;
    private static final int CRITERIA_FOR_PAWN_SCORE_ADJUSTMENT = 1;
    private static final double PAWN_SCORE_ADJUSTMENT = 0.5;

    private final List<Piece> pieces;

    public Pieces(final List<Piece> pieces) {
        this.pieces = pieces;
    }

    public Piece findPieceOrThrow(final Position position) {
        return findPiece(position)
                .orElseThrow(() -> new IllegalArgumentException("해당 위치에 말이 존재하지 않습니다."));
    }

    private Optional<Piece> findPiece(final Position position) {
        return pieces.stream()
                .filter(piece -> position.equals(piece.getPosition()))
                .findAny();
    }

    public Piece findPieceOrBlank(final Position position) {
        return findPiece(position)
                .orElseGet(() -> new BlankPiece(position.getFile(), position.getRank()));
    }

    public boolean hasPiece(List<Position> pathPositions) {
        return pieces.stream()
                .anyMatch(piece -> piece.existsIn(pathPositions));
    }

    public void add(final Piece piece) {
        pieces.add(piece);
    }

    public void remove(final Piece piece) {
        pieces.remove(piece);
    }

    public boolean isEmpty() {
        return pieces.isEmpty();
    }

    public boolean hasTwoKings() {
        return INITIAL_KING_COUNT == pieces.stream()
                .filter(Piece::isKing)
                .count();
    }

    public double calculateScore(final Color color) {
        final double pawnScore = calculatePawnScore(color);
        final double otherPiecesScore = calculateNonPawnScore(color);

        return pawnScore + otherPiecesScore;
    }

    private double calculatePawnScore(final Color color) {
        return Arrays.stream(File.values())
                .mapToDouble(file -> adjustSameFilePawnScore(file, color))
                .sum();
    }

    private double adjustSameFilePawnScore(final File file, final Color color) {
        final double score = calculateSameFilePawnScore(file, color);
        if (score > CRITERIA_FOR_PAWN_SCORE_ADJUSTMENT) {
            return score * PAWN_SCORE_ADJUSTMENT;
        }
        return score;
    }

    private double calculateSameFilePawnScore(final File file, final Color color) {
        return pieces.stream()
                .filter(piece -> piece.isSameColor(color))
                .filter(Piece::isPawn)
                .filter(piece -> piece.isInSameFile(file))
                .mapToDouble(Piece::getScore)
                .sum();
    }

    private double calculateNonPawnScore(final Color color) {
        return pieces.stream()
                .filter(piece -> !piece.isPawn() && piece.isSameColor(color))
                .mapToDouble(Piece::getScore)
                .sum();
    }

    public List<Piece> getPieces() {
        return List.copyOf(pieces);
    }
}
