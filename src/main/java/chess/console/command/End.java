package chess.console.command;

import chess.GameManager;
import chess.console.view.OutputView;
import chess.domain.position.StatusResult;

public final class End implements Command {

    @Override
    public void execute(GameManager gameManager) {
        StatusResult statusResult = gameManager.stop();
        OutputView.printStatus(statusResult);
    }

    @Override
    public boolean isEnd() {
        return true;
    }
}
