package chess.console.command;

import chess.console.view.OutputView;
import chess.domain.GameManager;

public final class Status implements Command {
    @Override
    public void execute(GameManager gameManager) {
        OutputView.printStatus(gameManager);
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
