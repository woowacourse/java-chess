package chess.service;

import chess.domain.board.Board;
import chess.domain.position.Position;
import java.util.List;

public class NotStarted implements State {

    private static final String NOT_STARTED_CANT_EXECUTE_START_MESSAGE =
            "시작되지 않은 상태에선 해당 명령을 실행할 수 없습니다.";
    private static final NotStarted INSTANCE = new NotStarted();

    private NotStarted() {
    }

    public static NotStarted getInstance() {
        return INSTANCE;
    }

    @Override
    public State start() {
        return Started.getInstance();
    }

    @Override
    public State move(final Board board, final List<Position> positions) {
        throw new UnsupportedOperationException(NOT_STARTED_CANT_EXECUTE_START_MESSAGE);
    }

    @Override
    public State end() {
        return End.getInstance();
    }
}
