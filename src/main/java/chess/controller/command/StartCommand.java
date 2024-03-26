package chess.controller.command;

import chess.domain.game.ChessGame;
import chess.view.OutputView;

public class StartCommand implements Command {

    private StartCommand() {
    }

    public static StartCommand of(String input) {
        return new StartCommand();
    }

    @Override
    public void execute(ChessGame game, OutputView outputView) {
        outputView.printChessBoardMessage(game.getBoard());
    }

    @Override
    public boolean isStart() {
        return true;
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
