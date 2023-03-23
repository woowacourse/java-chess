package chess.domain.state;

import chess.domain.state.command.Command;

public class End extends Finished {

    @Override
    public ChessState changeStateByCommand(final Command command) {
        throw new IllegalArgumentException("게임이 종료되었습니다.");
    }
}
