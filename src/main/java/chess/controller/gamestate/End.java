package chess.controller.gamestate;

import chess.domain.Camp;
import chess.domain.ChessBoard;
import chess.dto.CommandRequest;

public class End implements GameState {

    private static final String WRONG_CALLING_ERROR_MESSAGE = "종료된 게임입니다.";

    @Override
    public GameState start() {
        throw new IllegalStateException(WRONG_CALLING_ERROR_MESSAGE);
    }

    @Override
    public GameState play(final ChessBoard chessBoard, final CommandRequest commandRequest,
                          final Camp currentTurnCamp) {
        throw new IllegalStateException(WRONG_CALLING_ERROR_MESSAGE);
    }

    @Override
    public GameState end() {
        return new Ready();
    }

    @Override
    public boolean shouldRequestUserInput() {
        return false;
    }
}
