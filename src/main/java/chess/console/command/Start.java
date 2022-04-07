package chess.console.command;

import chess.console.view.OutputView;
import chess.domain.GameManager;

public final class Start implements Command {

    @Override
    public void execute(GameManager gameManager) {
        OutputView.printBoard(gameManager.getBoard());
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
