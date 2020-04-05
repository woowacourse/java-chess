package chess;

import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Team;
import chess.position.File;
import chess.position.Position;
import chess.position.Rank;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Scores {
    private static final int MINIMUM_COUNT_OF_LOWER_SCORE_PAWN = 2;

    private final double whiteScore;
    private final double blackScore;

    private Scores(double whiteScore, double blackScore) {
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
    }

    public static Scores calculateScores(Board board) {
        double whiteScore = calculateScoreOf(board, Team.WHITE);
        double blackScore = calculateScoreOf(board, Team.BLACK);
        return new Scores(whiteScore, blackScore);
    }

    public static double calculateScoreOf(Board board, Team team) {
        return Arrays.stream(File.valuesExceptNone())
                .mapToDouble(file -> scoreOfFile(board, file, team))
                .sum();
    }

    private static double scoreOfFile(Board board, File file, Team team) {
        List<Piece> sameTeamPiecesInSameFile = Arrays.stream(Rank.valuesExceptNone())
                .map(rank -> board.getPiece(Position.of(file, rank)))
                .filter(piece -> board.isSameTeamBetween(team, piece))
                .collect(Collectors.toList());

        int countOfPawn = (int) sameTeamPiecesInSameFile.stream()
                .filter(Piece::isPawn)
                .count();

        double rawScoreOfFile = sameTeamPiecesInSameFile.stream()
                .mapToDouble(Piece::getScore)
                .sum();

        double scoreOfFile = rawScoreOfFile;
        if (countOfPawn >= MINIMUM_COUNT_OF_LOWER_SCORE_PAWN) {
            scoreOfFile = rawScoreOfFile - Pawn.getLowerScore() * countOfPawn;
        }
        return scoreOfFile;
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public double getBlackScore() {
        return blackScore;
    }
}
