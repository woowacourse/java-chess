package chess.domain.winningstatus;

import chess.domain.piece.Team;

import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

public class WinningStatusByScore implements WinningStatus {

    private final Map<Team, Score> scores;

    public WinningStatusByScore(Map<Team, Score> scores) {
        this.scores = scores;
    }

    @Override
    public boolean isWinnerDetermined() {
        return false;
    }

    @Override
    public Map<Team, Score> getScores() {
        return Collections.unmodifiableMap(scores);
    }

    @Override
    public Team getWinner() {
        return scores.keySet()
                .stream()
                .max(Comparator.comparing(key -> scores.get(key).getScore()))
                .orElseThrow(IllegalArgumentException::new);
    }
}
