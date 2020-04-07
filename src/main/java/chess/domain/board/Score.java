package chess.domain.board;

import chess.domain.coordinate.Coordinate;
import chess.domain.coordinate.File;
import chess.domain.coordinate.Rank;
import chess.domain.piece.Team;

import java.util.function.Function;

public class Score {
    private static final int PAWN_REDUCIBLE_COUNT = 2;
    private static final double PAWN_REDUCE_SCORE = 0.5;
    private final double score;
    private final int pawnCount;

    private Score(final double score, final int pawnCount) {
        this.score = score;
        this.pawnCount = pawnCount;
    }

    public static Score zero() {
        return new Score(0, 0);
    }

    public static double calculateScore(Team team, Function<Coordinate, Tile> tileFinder) {
        Score sum = zero();
        for (File file : File.values()) {
            sum = sum.add(getSumFileScore(team, tileFinder, file));
            sum = sum.subtractPawnScore();
        }
        return sum.getScore();
    }

    public Score add(Tile tile, Team team) {
        if (!tile.isSameTeam(team)) {
            return this;
        }
        int pawnCount = this.pawnCount;
        if (tile.isPawn()) {
            pawnCount++;
        }
        return new Score(this.score + tile.getScore(), pawnCount);
    }

    private static Score getSumFileScore(Team team, Function<Coordinate, Tile> tileFinder, File file) {
        Score sum = zero();
        for (Rank rank : Rank.values()) {
            Coordinate coordinate = Coordinate.of(file, rank);
            sum = sum.add(tileFinder.apply(coordinate), team);
        }
        return sum;
    }

    private Score add(Score score) {
        return new Score(this.score + score.getScore(), this.pawnCount + score.pawnCount);
    }

    private Score subtractPawnScore() {
        double sum = this.score;
        if (this.pawnCount >= PAWN_REDUCIBLE_COUNT) {
            sum -= PAWN_REDUCE_SCORE * this.pawnCount;
        }
        return new Score(sum, 0);
    }

    public double getScore() {
        return score;
    }
}
