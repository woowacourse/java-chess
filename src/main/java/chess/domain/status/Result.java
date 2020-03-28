package chess.domain.status;

import chess.domain.Player;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Result {
    private final List<Status> statuses;
    private final Player winner;

    public Result(List<Status> statuses) {
        Objects.requireNonNull(statuses);
        this.statuses = statuses;
        this.winner = createWinner();
    }

    private Player createWinner() {
         return statuses.stream()
                .max(Comparator.comparingDouble(Status::getScore))
                .orElseThrow(() -> new IllegalArgumentException("계산 오류 입니다."))
                .getPlayer();
    }

    public Player getWinner() {
        return winner;
    }

    public List<Status> getStatuses() {
        return statuses;
    }
}