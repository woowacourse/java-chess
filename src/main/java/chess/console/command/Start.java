package chess.console.command;

import chess.GameManager;
import chess.console.view.OutputView;

public final class Start implements Command{

    @Override
    public void execute(GameManager gameManager) {
        gameManager.start();
        OutputView.printBoard(gameManager);
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
