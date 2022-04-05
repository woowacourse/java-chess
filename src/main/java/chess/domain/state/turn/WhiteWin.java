package chess.domain.state.turn;

import chess.domain.Team;

public class WhiteWin extends Finished{

    public WhiteWin() {
        super(Team.WHITE);
    }

    @Override
    public String toString() {
        return "WhiteWin";
    }
}
