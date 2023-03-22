package chess.domain.state;

import chess.domain.board.Turn;
import chess.domain.piece.Color;
import chess.domain.state.command.Command;

public class Initialize extends Runnable {

    @Override
    public ChessState changeStateByCommand(final Command command) {
        if (command.isStart()) {
            return new Run(new Turn(Color.WHITE));
        }
        throw new IllegalArgumentException("초기화되지 않아 명령을 수행할 수 없습니다.");
    }

    @Override
    public ChessState changeTurn() {
        throw new IllegalArgumentException();
    }

    @Override
    public boolean isInCorrectTurn(final Color color) {
        throw new IllegalArgumentException();
    }
}
