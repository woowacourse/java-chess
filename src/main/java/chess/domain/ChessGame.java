package chess.domain;

import chess.domain.piece.Team;
import chess.domain.player.Player;
import chess.domain.point.Point;
import java.util.Map;

public class ChessGame {

    private final Map<Team, Player> players;
    private Team turn;

    public ChessGame(Board board) {
        this.players = Map.of(
                Team.WHITE, new Player(Team.WHITE, board),
                Team.BLACK, new Player(Team.BLACK, board));
        this.turn = Team.WHITE;
    }

    public void currentTurnPlayerMove(Point departure, Point destination) {
        Player player = this.players.get(turn);
        player.move(departure, destination);
    }

    public void turnOver() {
        this.turn = turn.opponent();
    }
}
