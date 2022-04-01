package chess.domain.game;

import chess.domain.piece.ChessPiece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Score {

    private static final int INIT_PAWN_SCORE = 0;
    private static final double HALF_SCORE_OF_PAWN = 0.5;
    private static final int SCORE_OF_PAWN = 1;

    private final Map<Position, ChessPiece> currentChessboard;

    public Score(Map<Position, ChessPiece> currentChessboard) {
        this.currentChessboard = currentChessboard;
    }

    public Map<Color, Double> calculateScore() {
        Map<Color, Double> scoreByColor = new HashMap<>();
        for (Color color : Color.values()) {
            scoreByColor.put(color, getSum(color, currentChessboard));
        }
        return scoreByColor;
    }

    private double getSum(Color color, Map<Position, ChessPiece> chessBoard) {
        double sumExceptPawnScore = chessBoard.values().stream()
                .filter((chessPiece) -> chessPiece.isSameColor(color))
                .filter(this::isNotPawn)
                .mapToDouble(ChessPiece::getScore)
                .sum();

        return sumExceptPawnScore + getSumPawn(color);
    }

    private double getSumPawn(Color color) {
        double totalPawnScore = INIT_PAWN_SCORE;
        for (Rank rank : Rank.values()) {
            double pawnCount = countSameRankPawn(color, rank);
            totalPawnScore += sumPawnScore(pawnCount);
        }
        return totalPawnScore;
    }

    private double countSameRankPawn(Color color, Rank rank) {
        return Arrays.stream(File.values())
                .map((file) -> searchPieceInFile(rank, file))
                .filter((possiblePiece) -> isSameColorPawn(color, possiblePiece))
                .count();
    }

    private Optional<ChessPiece> searchPieceInFile(Rank rank, File file) {
        return Optional.ofNullable(currentChessboard.get(new Position(convertToString(rank, file))));
    }

    private String convertToString(Rank rank, File file) {
        return rank.getValue() + file.getValue();
    }

    private boolean isSameColorPawn(Color color, Optional<ChessPiece> possiblePiece) {
        if (possiblePiece.isEmpty()) {
            return false;
        }

        ChessPiece chessPiece = possiblePiece.get();

        return chessPiece.isPawn() && chessPiece.isSameColor(color);
    }

    private boolean isNotPawn(ChessPiece chessPiece) {
        return !(chessPiece.isPawn());
    }

    private double sumPawnScore(double pawnCount) {
        if (isOnlyPawnInFile(pawnCount)) {
            return SCORE_OF_PAWN;
        }
        return pawnCount * HALF_SCORE_OF_PAWN;
    }

    private boolean isOnlyPawnInFile(double pawnCount) {
        return pawnCount == 1;
    }

}
