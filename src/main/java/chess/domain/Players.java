package chess.domain;

import java.util.List;

public class Players {

    private final List<Player> players;

    private Players(final List<Player> players) {
        this.players = players;
    }

    public static Players from(final Player whitePlayer, final Player blackPlayer) {
        return new Players(List.of(whitePlayer, blackPlayer));
    }

    @Override
    public String toString() {
        return "Players{" +
                "players=" + players +
                '}';
    }
}
