package chess.controller.gamestate;

import chess.domain.Camp;
import chess.domain.ChessBoard;
import chess.dto.CommandRequest;

public class Ready implements GameState {

    private static final String WRONG_CALLING_ERROR_MESSAGE = "아직 시작되지 않은 게임입니다.";

    @Override
    public GameState start() {
        return new Running();
    }

    @Override
    public GameState play(final ChessBoard chessBoard, final CommandRequest commandRequest,
                          final Camp currentTurnCamp) {
        throw new IllegalStateException(WRONG_CALLING_ERROR_MESSAGE);
    }

    @Override
    public GameState end() {
        throw new IllegalStateException(WRONG_CALLING_ERROR_MESSAGE);
    }

    @Override
    public boolean shouldRequestUserInput() {
        return true;
    }
}
