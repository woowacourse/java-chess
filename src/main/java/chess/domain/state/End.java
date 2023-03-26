package chess.domain.state;

import chess.domain.board.Turn;
import chess.domain.state.command.Command;

public class End extends Finished {

    private final Turn winner;

    public End(final Turn turn) {
        this.winner = turn;
    }

    @Override
    public ChessState changeStateByCommand(final Command command) {
        throw new IllegalArgumentException("게임이 종료되었습니다.");
    }

    @Override
    public String findWinner() {
        return winner.convertToColorLabel();
    }
}
