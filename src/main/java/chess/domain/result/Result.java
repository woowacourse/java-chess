package chess.domain.result;

import chess.domain.game.Player;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Result {
    private final List<Score> scores;
    private final List<Player> winners;
    private final boolean isDraw;
    private final boolean isGameFinished;

    public Result(List<Score> scores) {
        Objects.requireNonNull(scores);
        this.scores = scores;
        this.winners = createWinners();
        this.isDraw = winners.size() == scores.size();
        this.isGameFinished = isGameFinished(scores);
    }

    private List<Player> createWinners() {
        double maxScore = scores.stream()
                .max(Comparator.comparingDouble(Score::getScore))
                .orElseThrow(() -> new IllegalArgumentException("계산 오류 입니다."))
                .getScore();

        List<Player> winners = scores.stream()
                .filter(status -> status.isSameScore(maxScore))
                .map(Score::getPlayer)
                .collect(Collectors.toList());

      return winners;
    }

    private boolean isGameFinished(List<Score> scores) {
        return scores.stream()
                .anyMatch(score -> score.getScore() == 0);
    }

    public List<Player> getWinners() {
        return winners;
    }

    public List<Score> getScores() {
        return scores;
    }

    public boolean isDraw() {
        return isDraw;
    }

    public boolean isGameFinished() {
        return isGameFinished;
    }

    public double getWhiteScore() {
        return scores.get(0).getScore();
    }

    public double getBlackScore() {
        return scores.get(1).getScore();
    }

}