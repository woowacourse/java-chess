package chess.domain.player;

import chess.domain.piece.Owner;
import chess.domain.piece.Score;

import java.util.*;
import java.util.stream.Collectors;

public class Scores {
    private final Map<Player, Score> scoreTable;

    public Scores(){
        this.scoreTable = new HashMap<>();
    }

    private Scores(final Map<Player, Score> scoreTable){
        this.scoreTable = scoreTable;
    }

    public Scores update(final Player player, final Score score) {
        final Map<Player, Score> scores = new HashMap<>(scoreTable);
        scores.put(player, score);
        return new Scores(scores);
    }

    public List<Owner> winner(){
        final Score max = maxScore();

        return players().stream()
                .filter(player -> scoreTable.get(player).equals(max))
                .map(player -> player.owner())
                .collect(Collectors.toList());
    }

    private Score maxScore(){
        Score max = new Score(0);
        for(final Player player : players()){
            max = max.bigger(scoreTable.get(player));
        }
        return max;
    }

    public Set<Player> players(){
        return scoreTable.keySet();
    }

    public Score get(final Player player){
        return scoreTable.get(player);
    }
}
