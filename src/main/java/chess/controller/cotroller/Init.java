package chess.controller.cotroller;

import chess.controller.Command;
import chess.domain.game.ChessGame;
import chess.view.OutputView;

public final class Init extends ChessController {


    private static final String INVALID_COMMEND_MESSAGE = "end 혹은 start 만 입력할 수 있습니다.";

    public Init() {
        OutputView.printInitMessage();
    }

    public void start() {
        go(new ChessGame());
    }

    @Override
    protected void execute(ChessGame chessGame) {
        Command command = getCommand();
        if (command == Command.END) {
            new End(chessGame);
            return;
        }
        if (command == Command.START) {
            chessGame.start();
            new Play(chessGame);
            return;
        }
        throw new IllegalArgumentException(INVALID_COMMEND_MESSAGE);
    }
}
