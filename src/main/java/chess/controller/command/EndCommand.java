package chess.controller.command;

import chess.domain.game.ChessGame;
import chess.view.OutputView;

public class EndCommand implements Command {
    private EndCommand() {
    }

    public static EndCommand of(String input) {
        return new EndCommand();
    }

    @Override
    public void execute(ChessGame game, OutputView outputView) {
    }

    @Override
    public boolean isStart() {
        return false;
    }

    @Override
    public boolean isEnd() {
        return true;
    }
}
