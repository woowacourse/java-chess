package chess.domain.state;

import chess.Command;
import chess.domain.Status;
import chess.domain.board.Board;
import chess.domain.board.strategy.CreateCompleteBoardStrategy;

public class Ready implements ChessState {

    private static final String CANNOT_IMPLEMENT_COMMAND = "현재 실행할 수 없는 명령입니다.";

    @Override
    public ChessState execute(final Command command, String... commandArgs) {
        if (!(command == Command.START)) {
            throw new IllegalArgumentException(CANNOT_IMPLEMENT_COMMAND);
        }
        return new WhiteRunning(new Board(new CreateCompleteBoardStrategy()));
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public Board getBoard() {
        throw new UnsupportedOperationException(CANNOT_IMPLEMENT_COMMAND);
    }

    @Override
    public Status createStatus() {
        throw new UnsupportedOperationException(CANNOT_IMPLEMENT_COMMAND);
    }
}
