package chess.domain.state;

import chess.domain.piece.Color;
import chess.domain.state.command.Command;

public abstract class Finished implements ChessState {

    @Override
    public boolean isRunnable() {
        return false;
    }

    @Override
    public ChessState changeTurn() {
        throw new IllegalArgumentException();
    }

    @Override
    public boolean isInCorrectTurn(final Color color) {
        throw new IllegalArgumentException();
    }

    @Override
    public ChessState finish() {
        throw new IllegalStateException();
    }

    @Override
    public ChessState changeStateByCommand(final Command command) {
        throw new IllegalArgumentException("게임이 종료되었습니다.");
    }
}
