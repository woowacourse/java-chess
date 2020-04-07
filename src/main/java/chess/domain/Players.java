package chess.domain;

import java.util.HashMap;
import java.util.Map;

import chess.domain.piece.Side;
import chess.domain.player.Player;

public class Players {
    private Map<Side, Player> players;

    public Players(Player white, Player black) {
        this.players = new HashMap<>();
        players.put(Side.WHITE, white);
        players.put(Side.BLACK, black);
    }

    public Player get(Side side) {
        return players.get(side);
    }
}
