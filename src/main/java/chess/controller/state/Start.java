package chess.controller.state;

import chess.domain.ChessBoard;
import chess.dto.ChessBoardDto;
import java.util.List;

public class Start implements State {

    private static final String WRONG_REQUEST_ERROR_MESSAGE = "게임이 시작되지 않았습니다.";

    @Override
    public State start() {
        return new Progress(new ChessBoard());
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
        return true;
    }

    @Override
    public ChessBoardDto getBoard() {
        throw new IllegalArgumentException(WRONG_REQUEST_ERROR_MESSAGE);
    }

}
