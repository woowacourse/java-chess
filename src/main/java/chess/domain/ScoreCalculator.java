package chess.domain;

import chess.domain.piece.Role;
import chess.domain.position.File;
import java.util.List;

public class ScoreCalculator {


    public static double calculateScore(final List<Square> squares, final Team team) {
        double heavyPiecesScore = calculateHeavyPiecesScore(squares, team);
        double pawnScore = calculatePawnScore(squares, team);
        return heavyPiecesScore + pawnScore;
    }

    private static double calculateHeavyPiecesScore(final List<Square> squares, final Team team) {
        return squares.stream().filter(square -> square.isSameTeam(team))
                .filter(square -> !square.hasSameRole(Role.PAWN)).mapToDouble(Square::getScore).sum();
    }

    private static double calculatePawnScore(final List<Square> squares, final Team team) {
        double pawnScore = 0;
        for (File file : File.values()) {
            pawnScore += calculatePawnScoreOfFile(squares, file, team);
        }
        return pawnScore;
    }

    private static double calculatePawnScoreOfFile(final List<Square> squares, final File file, final Team team) {
        double pawnScore = squares.stream().filter(square -> square.isSameTeam(team))
                .filter(square -> square.isSameFile(file)).filter(square -> square.hasSameRole(Role.PAWN))
                .mapToDouble(Square::getScore).sum();
        if (pawnScore > 1) {
            return pawnScore / 2;
        }
        return pawnScore;
    }
}
