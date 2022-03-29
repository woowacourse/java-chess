package chess.domain.state.turn;

import chess.domain.Team;

public class Finish extends Finished{

    protected Finish() {
        super(Team.NEUTRALITY);
    }

}
