package chess.domain;

import chess.domain.chessPiece.ChessPiece;
import chess.domain.chessPiece.Color;
import chess.domain.chessPiece.Pawn;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Score {

    private static final int INIT_PAWN_SCORE = 0;
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
                .filter((chessPiece) -> !(chessPiece instanceof Pawn))
                .mapToDouble(ChessPiece::getValue)
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
                .map((file) -> searchPieceInFile(rank,file))
                .filter((possiblePiece) -> isMyPawn(color, possiblePiece))
                .count();
    }

    private Optional<ChessPiece> searchPieceInFile(Rank rank, File file){
         return Optional.ofNullable(currentChessboard.get(new Position(convertToString(rank, file))));
    }

    private String convertToString(Rank rank, File file) {
        return rank.getValue() + file.getValue();
    }

    private boolean isMyPawn(Color color, Optional<ChessPiece> possiblePiece) {
        if (possiblePiece.isEmpty()) {
            return false;
        }

        ChessPiece chessPiece = possiblePiece.get();
        if (!(chessPiece instanceof Pawn) || !chessPiece.isSameColor(color)) {
            return false;
        }

        return true;
    }

    private double sumPawnScore(double pawnCount) {
        if (isOnlyPawnInFile(pawnCount)) {
            return 1;
        }
        return pawnCount * 0.5;
    }

    private boolean isOnlyPawnInFile(double pawnCount) {
        return pawnCount == 1;
    }

}
