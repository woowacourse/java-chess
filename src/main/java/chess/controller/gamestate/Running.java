package chess.controller.gamestate;

import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.dto.CommandRequest;

public class Running implements GameState {

    private static final String WRONG_CALLING_ERROR_MESSAGE = "이미 시작된 게임입니다.";

    @Override
    public GameState start() {
        throw new IllegalStateException(WRONG_CALLING_ERROR_MESSAGE);
    }

    @Override
    public GameState play(final ChessBoard chessBoard, final CommandRequest commandRequest) {
        chessBoard.move(Position.from(commandRequest.getSourceCoordinate()),
                Position.from(commandRequest.getDestinationCoordinate()));
        return new Running();
    }

    @Override
    public GameState end() {
        return new Ready();
    }

}
