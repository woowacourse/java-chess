package chess.domain.game.state;

import chess.controller.Command;
import chess.domain.game.ChessGame;

public final class Result extends State {
    private static final String INVALID_COMMEND_MESSAGE = "status 를 입력하세요.";

    public Result(ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    protected State execute(String input) {
        Command command = Command.from(input);
        if (command == Command.STATUS) {
            return new StatusEnd(chessGame);
        }
        throw new IllegalArgumentException(INVALID_COMMEND_MESSAGE);
    }
}
