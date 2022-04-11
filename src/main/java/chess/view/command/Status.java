package chess.view.command;

import chess.view.OutputView;
import chess.domain.GameManager;

public final class Status implements Command {
    @Override
    public void execute(GameManager gameManager) {
        OutputView.printStatus(gameManager.getStatus());
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
