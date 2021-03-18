package chess;

import chess.domain.grid.Grid;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleChessApplication {
    public static void main(String[] args) {
        OutputView.printStartChess();
        String command = InputView.inputCommand();
        if (command.equals("end")) {
            return;
        }
        Grid grid = new Grid();
        OutputView.printGridStatus(grid);
    }
}
