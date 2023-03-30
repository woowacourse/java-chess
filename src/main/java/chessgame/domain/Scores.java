package chessgame.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Scores {
    private final Set<Score> scores;

    public Scores() {
        this.scores = new HashSet<>();
    }

    public void set(Team team, double score) {
        scores.remove(new Score(team, score));
        scores.add(new Score(team, score));
    }

    public Set<Score> get() {
        return scores;
    }

    public List<Team> winner() {
        Score max = Collections.max(scores);
        Score min = Collections.min(scores);

        if (max == min) {
            return scores.stream().map(Score::team).collect(Collectors.toList());
        }
        return List.of(max.team());
    }
}
