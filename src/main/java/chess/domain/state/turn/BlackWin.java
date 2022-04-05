package chess.domain.state.turn;

import chess.domain.Team;

public class BlackWin extends Finished{
    public BlackWin() {
        super(Team.BLACK);
    }

    @Override
    public String toString() {
        return "BlackWin";
    }
}
