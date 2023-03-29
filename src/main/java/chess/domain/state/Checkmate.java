package chess.domain.state;

import chess.domain.board.Turn;

public class Checkmate extends Finished{

    public Checkmate(final Turn turn) {
        this.turn = turn;
    }

    @Override
    public boolean isCheckmate() {
        return true;
    }

    @Override
    public String findCurrentTurn() {
        return turn.convertToColorLabel();
    }
}
