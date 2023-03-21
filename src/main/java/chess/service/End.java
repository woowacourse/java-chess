package chess.service;

import chess.domain.board.Board;
import chess.domain.position.Position;
import java.util.List;

public class End implements State {

    private static final String END_CANT_EXECUTE_COMMAND_MESSAGE = "종료 상태에선 명령을 실행할 수 없습니다.";
    private static final End INSTANCE = new End();

    private End() {
    }

    public static End getInstance() {
        return INSTANCE;
    }

    @Override
    public State start() {
        throw new UnsupportedOperationException(END_CANT_EXECUTE_COMMAND_MESSAGE);
    }

    @Override
    public State move(final Board board, final List<Position> positions) {
        throw new UnsupportedOperationException(END_CANT_EXECUTE_COMMAND_MESSAGE);
    }

    @Override
    public State end() {
        throw new UnsupportedOperationException(END_CANT_EXECUTE_COMMAND_MESSAGE);
    }
}
