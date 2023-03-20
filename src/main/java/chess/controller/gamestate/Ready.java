package chess.controller.gamestate;

import chess.domain.ChessBoard;
import chess.dto.CommandRequest;

public class Ready implements GameState {

    private static final String WRONG_CALLING_ERROR_MESSAGE = "게임이 실행중이지 않습니다.";

    @Override
    public GameState start() {
        return new Running();
    }

    @Override
    public GameState play(final ChessBoard chessBoard, final CommandRequest commandRequest) {
        throw new IllegalStateException(WRONG_CALLING_ERROR_MESSAGE);
    }

    @Override
    public GameState end() {
        throw new IllegalStateException(WRONG_CALLING_ERROR_MESSAGE);
    }

}
