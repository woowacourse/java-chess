package chess.domain.state;

import chess.domain.piece.Team;

public class Finish extends Finished{

    protected Finish() {
        super(Team.NEUTRALITY);
    }

}
