package chess.board;

import chess.coordinate.Coordinate;
import chess.coordinate.File;
import chess.coordinate.Rank;
import chess.piece.Team;

import java.util.function.Function;

public class Score {
    private static final int PAWN_REDUCIBLE_COUNT = 2;
    private static final double PAWN_REDUCE_SCORE = 0.5;

    private final double sum;
    private final int pawnCount;

    private Score(final double sum, final int pawnCount) {
        this.sum = sum;
        this.pawnCount = pawnCount;
    }

    public static Score zero() {
        return new Score(0, 0);
    }

    public static Score calculateScore(Team team, Function<Coordinate, Tile> tileFinder) {
        Score sum = zero();
        for (File file : File.values()) {
            Score score = getScore(team, tileFinder, file);
            sum = sum.union(score);
        }
        return sum;
    }

    private static Score getScore(Team team, Function<Coordinate, Tile> tileFinder, File file) {
        Score score = Score.zero();
        for (Rank rank : Rank.values()) {
            Coordinate coordinate = Coordinate.of(file, rank);
            score = score.add(tileFinder.apply(coordinate), team);
        }
        return score.subtractPawnScore();
    }

    private Score union(Score score) {
        return new Score(this.sum + score.sum, this.pawnCount + score.pawnCount);
    }

    private Score add(Tile tile, Team team) {
        if (tile.isNotSameTeam(team)) {
            return this;
        }
        return new Score(this.sum + tile.getScore(), this.pawnCount + getPawnCount(tile));
    }

    private int getPawnCount(Tile tile) {
        if (tile.isPawn()) {
            return 1;
        }
        return 0;
    }

    private Score subtractPawnScore() {
        double sum = this.sum;
        if (this.pawnCount >= PAWN_REDUCIBLE_COUNT) {
            sum -= PAWN_REDUCE_SCORE * this.pawnCount;
        }
        return new Score(sum, 0);
    }

    public double getSum() {
        return sum;
    }
}
