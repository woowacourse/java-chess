package chess.domain.score;

import chess.domain.Team;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Score {

    private final Map<Team, Double> scores;
    private final boolean isDraw;
    private final Team winner;

    Score(final Team winner) {
        this.isDraw = false;
        this.winner = winner;
        scores = new HashMap<>();
    }

    Score(final Map<Team, Double> scores) {
        this.isDraw = isDraw(scores);
        this.scores = Collections.unmodifiableMap(scores);
        if (isDraw) {
            winner = null;
            return;
        }
        this.winner = findAnyMaxScoreEntry(scores).getKey();
    }

    private boolean isDraw(final Map<Team, Double> scores) {
        Entry<Team, Double> maxTeamScore = findAnyMaxScoreEntry(scores);

        return scores.entrySet().stream()
            .anyMatch(entry -> !entry.getKey().equals(maxTeamScore.getKey())
                && entry.getValue().equals(maxTeamScore.getValue()));
    }

    private Entry<Team, Double> findAnyMaxScoreEntry(final Map<Team, Double> scores) {
        return scores.entrySet().stream()
            .max(Entry.comparingByValue())
            .orElseThrow(() -> new IllegalStateException("한 팀의 킹이 죽어서 점수정보가 없습니다."));
    }

    public Map<String, Double> getScores() {
        Map<String, Double> scores = new HashMap<>();
        this.scores.forEach((key, value) -> scores.put(key.toString(), value));
        return Collections.unmodifiableMap(scores);
    }

    public String getWinner() {
        System.out.println(this.isDraw);
        if (this.isDraw) {
            throw new IllegalStateException("무승부일 때 승자를 얻을 수 없습니다.");
        }
        return winner.toString();
    }

    public boolean isDraw() {
        return this.isDraw;
    }
}
