package chess;

import chess.view.InputView;
import chess.view.OutputView;

public class Application {

    public static void main(String[] args) {
        Board board = new Board();
        OutputView outputView = new OutputView();
        InputView inputView = new InputView();
        outputView.printStartMessage();
        outputView.printCommandInstruction();
        Command command = inputView.inputPlayerCommand();
        while (command.isStart()) {
            outputView.printBoard(board.getBoard());
            command = inputView.inputPlayerCommand();
        }
    }
}
