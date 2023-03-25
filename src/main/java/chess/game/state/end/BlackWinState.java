package chess.game.state.end;

import chess.domain.Team;

public class BlackWinState extends EndState {
    public static final EndState STATE = new BlackWinState();

    private BlackWinState() {
    }

    @Override
    public Team getWinner() {
        return Team.BLACK;
    }
}
