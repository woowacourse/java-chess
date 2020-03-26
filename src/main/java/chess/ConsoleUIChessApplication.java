package chess;

import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleUIChessApplication {
    public static void main(String[] args) {
        OutputView.showAllCommand();

        Command command = new Command(InputView::inputCommand);
        while (command.isNotEnd()) {
            command.action();
        }
    }
}
