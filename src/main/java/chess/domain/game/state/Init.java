package chess.domain.game.state;

import chess.controller.Command;
import chess.domain.game.ChessGame;

public final class Init extends State {
    private static final String INVALID_COMMEND_MESSAGE = "end 혹은 start 만 입력할 수 있습니다.";

    public Init(ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    protected State execute(String input) {
        Command command = Command.from(input);
        if (command == Command.END) {
            return new ExitEnd(chessGame);
        }
        if (command == Command.START) {
            chessGame.start();
            return new Play(chessGame);
        }
        throw new IllegalArgumentException(INVALID_COMMEND_MESSAGE);
    }
}
