package controller;

import domain.ChessBoard;
import domain.Command;
import view.InputView;
import view.OutputView;

public class ChessManager {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessManager(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        Command command = inputView.readCommand();
        if (command == Command.START) {
            ChessBoard chessBoard = new ChessBoard();
            chessBoard.init();
            outputView.printChessBoard(chessBoard);
        }
    }
}
