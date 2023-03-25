package chess.game.state.end;

import chess.domain.Team;

public class WhiteWinState extends EndState {
    public static final EndState STATE = new WhiteWinState();

    private WhiteWinState() {
    }

    @Override
    public Team getWinner() {
        return Team.WHITE;
    }
}
