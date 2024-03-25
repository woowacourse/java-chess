package chess.domain.player;

import chess.domain.piece.Team;
import java.util.Map;

public class Players {

    private final Map<Team, Player> players;
    private Team turn;

    public Players(Player first, Player second) {
        this.players = Map.of(
                Team.WHITE, first,
                Team.BLACK, second
        );
        this.turn = Team.WHITE;
    }

    public void turnOver() {
        turn = turn.opponent();
    }

    public Player playerOfCurrentTurn() {
        return players.get(turn);
    }
}
