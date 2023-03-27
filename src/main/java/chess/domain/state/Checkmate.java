package chess.domain.state;

import chess.domain.board.Turn;

public class Checkmate extends Finished{

    private final Turn winner;

    public Checkmate(final Turn turn) {
        this.winner = turn;
    }

    @Override
    public String findCurrentTurn() {
        return winner.convertToColorLabel();
    }
}
