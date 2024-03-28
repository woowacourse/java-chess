package chess.model.score;

import chess.dto.ScoreDTO;
import chess.model.board.Board;
import chess.model.piece.Color;
import chess.model.piece.Pawn;
import chess.model.piece.Piece;
import chess.model.piece.Type;

import java.util.Map;
import java.util.stream.IntStream;

public class ScoreGenerator {
    private static final int PAWN_REDUCE_THRESHOLD = 2;
    private static final double REDUCED_PAWN_SCORE = 0.5;

    private final Board board;

    public ScoreGenerator(Board board) {
        this.board = board;
    }

    public ScoreDTO calculateScore() {
        double blackScore = calculateScore(Color.BLACK);
        double whiteScore = calculateScore(Color.WHITE);
        return new ScoreDTO(blackScore, whiteScore);
    }

    private double calculateScore(Color color) {
        Map<Piece, Integer> pieceCount = board.getPieceCount(color);
        double pieceScore = calculatePieceScore(pieceCount);
        double reducedPawnScore = calculateReducedPawnScore(color);
        return pieceScore - reducedPawnScore;
    }

    private double calculatePieceScore(Map<Piece, Integer> pieceCount) {
        double totalScore = 0;
        for (Piece piece : pieceCount.keySet()) {
            Type type = Type.from(piece);
            double score = Score.from(type);
            totalScore += (score * pieceCount.get(piece));
        }
        return totalScore;
    }

    private double calculateReducedPawnScore(Color color) {
        Piece pawn = Pawn.from(color);
        int sameFilePawnCount = IntStream.rangeClosed(Board.MIN_LENGTH, Board.MAX_LENGTH)
                .map(file -> board.countPieceOfFile(pawn, file))
                .filter(pawnCount -> pawnCount >= PAWN_REDUCE_THRESHOLD)
                .sum();
        return sameFilePawnCount * REDUCED_PAWN_SCORE;
    }
}
