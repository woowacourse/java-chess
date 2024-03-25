package chess.domain.player;

import java.util.List;

public class Players {
    private final List<Player> players;
    private int turn;

    public Players(Player first, Player second) {
        this.players = List.of(first, second);
        this.turn = 0;
    }

    public void turnOver() {
        this.turn++;
    }

    public Player currentTurnOfPlayer() {
        return players.get(turn % 2);
    }
}
