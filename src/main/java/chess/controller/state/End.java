package chess.controller.state;

import chess.dto.ChessBoardDto;
import java.util.List;

public class End implements State {

    private static final String WRONG_REQUEST_ERROR_MESSAGE = "게임이 진행 중일 때는 시작할 수 없습니다.";

    @Override
    public State start() {
        throw new IllegalArgumentException(WRONG_REQUEST_ERROR_MESSAGE);
    }

    @Override
    public void move(List<Integer> source, List<Integer> dest) {
        throw new IllegalArgumentException(WRONG_REQUEST_ERROR_MESSAGE);
    }

    @Override
    public State end() {
        throw new IllegalArgumentException(WRONG_REQUEST_ERROR_MESSAGE);
    }

    @Override
    public boolean isNotEnd() {
        return false;
    }

    @Override
    public ChessBoardDto getBoard() {
        throw new IllegalArgumentException(WRONG_REQUEST_ERROR_MESSAGE);
    }

}
