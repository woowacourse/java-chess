package chess.service;

import chess.domain.board.Board;
import chess.domain.position.Position;
import java.util.List;

public class Started implements State {

    private static final Started INSTANCE = new Started();
    private static final String STARTED_CANT_EXECUTE_START_MESSAGE = "시작된 상태에선 해당 명령을 실행할 수 없습니다.";
    private static final int FROM_INDEX = 0;
    private static final int TO_INDEX = 1;

    private Started() {
    }

    public static Started getInstance() {
        return INSTANCE;
    }

    @Override
    public State start() {
        throw new UnsupportedOperationException(STARTED_CANT_EXECUTE_START_MESSAGE);
    }

    @Override
    public State move(final Board board, final List<Position> positions) {
        board.move(positions.get(FROM_INDEX), positions.get(TO_INDEX));
        return this;
    }

    @Override
    public State end() {
        return End.getInstance();
    }
}
