package chess.domain.state;

import chess.domain.board.Turn;

public class End extends Finished {

    public End(final Turn turn) {
        this.turn = turn;
    }

    @Override
    public boolean isCheckmate() {
        return false;
    }

    @Override
    public String findCurrentTurn() {
        return turn.convertToColorLabel();
    }
}
