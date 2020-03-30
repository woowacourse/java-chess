package chess.domain.result;

import chess.domain.player.Player;

import java.util.Map;

public class Status {
    private final Map<Player, Double> status;

    public Status(Map<Player, Double> status) {
        this.status = status;
    }

    public Map<Player, Double> getStatus() {
        return status;
    }

    public Player getWinner() {
        if (status.get(Player.WHITE) > status.get(Player.BLACK)) {
            return Player.WHITE;
        }
        return Player.BLACK;

    }
}
