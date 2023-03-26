package chessgame.domain;

import java.util.Objects;

public class Score implements Comparable<Score> {
    private final Team team;
    private final double score;

    public Score(Team team, double score) {
        this.team = team;
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Score score1 = (Score)o;
        return team == score1.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(team);
    }

    @Override
    public int compareTo(Score other) {
        return (this.score > other.score) ? 1 : (score == other.score ? 0 : -1);
    }

    public Team team() {
        return team;
    }

    public double score() {
        return score;
    }
}
