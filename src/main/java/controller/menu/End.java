package controller.menu;

import domain.ChessGame;
import view.OutputView;

public class End implements Command {
    @Override
    public void execute(String command, ChessGame game) {
        game.end();
        OutputView.showEndMessage();
    }
}
