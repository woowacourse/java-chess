package chess.domain.player;

import chess.domain.piece.Owner;
import chess.domain.piece.Score;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Scores {
    private final Map<Player, Score> scoreTable;

    public Scores() {
        this.scoreTable = new HashMap<>();
    }

    private Scores(final Map<Player, Score> scoreTable) {
        this.scoreTable = scoreTable;
    }

    public Scores update(final Player player, final Score score) {
        final Map<Player, Score> scores = new HashMap<>(scoreTable);
        scores.put(player, score);
        return new Scores(scores);
    }

    public List<Owner> winner() {
        final Score max = maxScore();
        return players().stream()
                .filter(player -> scoreTable.get(player).equals(max))
                .map(player -> player.owner())
                .collect(Collectors.toList());
    }

    private Score maxScore() {
        Score max = Score.EMPTY;
        for (final Player player : players()) {
            max = max.bigger(scoreTable.get(player));
        }
        return max;
    }

    public Set<Player> players() {
        return scoreTable.keySet();
    }

    public Score get(final Player player) {
        return scoreTable.get(player);
    }

    public double getValueOf(final Owner owner) {
        return players().stream()
                .filter(player -> player.isOwner(owner))
                .map(player -> get(player).score())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("적절하지 않은 진영 입력입니다."));
    }
}
