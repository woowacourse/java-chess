package chess.domain.state.turn;

import chess.domain.Team;

public class End extends Finished{

    public End() {
        super(Team.NEUTRALITY);
    }

    @Override
    public boolean isEnd() {
        return true;
    }
}
