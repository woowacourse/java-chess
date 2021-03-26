package chess.domain.team;

import chess.domain.Position;
import chess.domain.piece.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Score {
    private static final Map<Piece, Double> scoreByPiece = new HashMap<>();

    private static final double SINGLE_KING_SCORE = 0.0;
    private static final double SINGLE_QUEEN_SCORE = 9.0;
    private static final double SINGLE_ROOK_SCORE = 5.0;
    private static final double SINGLE_BISHOP_SCORE = 3.0;
    private static final double SINGLE_KNIGHT_SCORE = 2.5;
    private static final double SINGLE_PAWN_SCORE = 1.0;

    private static final int MIN_COLUMN = 0;
    private static final int MAX_COLUMN = 7;

    static {
        scoreByPiece.put(new King(), SINGLE_KING_SCORE);
        scoreByPiece.put(new Queen(), SINGLE_QUEEN_SCORE);
        scoreByPiece.put(new Rook(), SINGLE_ROOK_SCORE);
        scoreByPiece.put(new Bishop(), SINGLE_BISHOP_SCORE);
        scoreByPiece.put(new Knight(), SINGLE_KNIGHT_SCORE);
        scoreByPiece.put(new Pawn(1), SINGLE_PAWN_SCORE);
        scoreByPiece.put(new Pawn(-1), SINGLE_PAWN_SCORE);
    }

    public Score() {
    }

    public double calculateScore(final Map<Position, Piece> piecePosition) {
        double totalScore = 0;
        for (int column = MIN_COLUMN; column <= MAX_COLUMN; column++) {
            final List<Piece> pieceInSameColumn = collectPieceInSameColumn(piecePosition, column);
            totalScore += calculateScore(pieceInSameColumn);
        }
        return totalScore;
    }

    private List<Piece> collectPieceInSameColumn(final Map<Position, Piece> piecePosition, final int column) {
        return piecePosition.keySet().stream()
                .filter(position -> position.checkSameColumn(column))
                .map(piecePosition::get)
                .collect(Collectors.toList());
    }

    private double calculateScore(final List<Piece> pieces) {
        final double scoreExceptPawn = calculateScoreExceptPawn(pieces);
        final double pawnScore = calculatePawnScore(pieces);
        if (pawnScore > SINGLE_PAWN_SCORE) {
            return scoreExceptPawn + (pawnScore / 2);
        }
        return scoreExceptPawn + pawnScore;
    }

    private double calculateScoreExceptPawn(final List<Piece> pieces) {
        return pieces.stream()
                .filter(piece -> !piece.isPawn())
                .mapToDouble(scoreByPiece::get)
                .sum();
    }

    private double calculatePawnScore(final List<Piece> pieces) {
        return pieces.stream()
                .filter(Piece::isPawn)
                .mapToDouble(scoreByPiece::get)
                .sum();
    }
}
