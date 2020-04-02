package chess.domain.status;

import chess.domain.game.Player;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Result {
    private final List<Status> statuses;
    private final List<Player> winners;
    private final boolean isDraw;

    public Result(List<Status> statuses) {
        Objects.requireNonNull(statuses);
        this.statuses = statuses;
        this.winners = createWinners();
        this.isDraw = winners.size() == statuses.size();
    }

    private List<Player> createWinners() {
        double maxScore = statuses.stream()
                .max(Comparator.comparingDouble(Status::getScore))
                .orElseThrow(() -> new IllegalArgumentException("계산 오류 입니다."))
                .getScore();

        List<Player> winners = statuses.stream()
                .filter(status -> status.isSameScore(maxScore))
                .map(Status::getPlayer)
                .collect(Collectors.toList());

        return winners;
    }

    public List<Player> getWinners() {
        return winners;
    }

    public List<Status> getStatuses() {
        return statuses;
    }

    public boolean isDraw() {
        return isDraw;
    }
}