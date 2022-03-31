package chess.console.command;

import chess.console.GameManager;
import chess.view.OutputView;

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
