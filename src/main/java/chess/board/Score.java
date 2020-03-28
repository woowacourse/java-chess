package chess.board;

import chess.coordinate.Coordinate;
import chess.coordinate.File;
import chess.coordinate.Rank;
import chess.piece.Team;

import java.util.function.Function;

public class Score {
    private final double sum;
    private final int pawnCount;

    private Score(final double sum, final int pawnCount) {
        this.sum = sum;
        this.pawnCount = pawnCount;
    }

    public static Score zero() {
        return new Score(0, 0);
    }

    public Score add(Tile tile, Team team) {
        if (!tile.isSameTeam(team)) {
            return this;
        }
        int pawnCount = this.pawnCount;
        if (tile.isPawn()) {
            pawnCount++;
        }
        return new Score(this.sum + tile.getScore(), pawnCount);
    }

    public Score subtractPawnScore() {
        double sum = this.sum;
        if (this.pawnCount >= 2) {
            sum -= 0.5 * this.pawnCount;
        }
        return new Score(sum, 0);
    }

    public static double calculateScore(Team team, Function<Coordinate, Tile> tileFinder) {
        Score sum = zero();
        for (File file : File.values()) {
            for (Rank rank : Rank.values()) {
                Coordinate coordinate = Coordinate.of(file, rank);
                sum = sum.add(tileFinder.apply(coordinate), team);
            }
            sum = sum.subtractPawnScore();
        }
        return sum.getSum();
    }

    public double getSum() {
        return sum;
    }
}
