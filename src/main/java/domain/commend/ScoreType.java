package domain.commend;

import domain.pieces.Pieces;
import domain.point.Point;
import domain.team.Team;
import java.util.Arrays;

public enum ScoreType {
    KING("k", 0),
    QUEEN("q", 9),
    ROOK("r", 5),
    BISHOP("b", 3),
    KNIGHT("n", 2.5),
    PAWN("p", 1),
    NONE(".", 0);

    private String initial;
    private double score;

    ScoreType(String initial, double score) {
        this.initial = initial;
        this.score = score;
    }

    public static double calculateBlackScore(Pieces pieces) {
        double totalScore = pieces.getPieces().keySet().stream()
            .filter(point -> pieces.getPieces().get(point).isSameTeam(Team.BLACK))
            .mapToDouble(point -> ATMBlack(pieces, point))
            .reduce(0, Double::sum);
        return totalScore;
    }

    private static double ATMBlack(Pieces pieces, Point point) {
        return Arrays.stream(ScoreType.values())
            .filter(x -> pieces.getPieces().get(point).getInitial().equals(x.initial.toUpperCase()))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(""))
            .score;
    }

    public static double calculateWhiteScore(Pieces pieces) {
        double totalScore = pieces.getPieces().keySet().stream()
            .filter(point -> pieces.getPieces().get(point).isSameTeam(Team.WHITE))
            .mapToDouble(point -> ATMWhite(pieces, point))
            .reduce(0, Double::sum);
        return totalScore;
    }

    private static double ATMWhite(Pieces pieces, Point point) {
        return Arrays.stream(ScoreType.values())
            .filter(x -> pieces.getPieces().get(point).getInitial().equals(x.initial.toLowerCase()))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(""))
            .score;
    }
}
