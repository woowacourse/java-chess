package controller;

import domain.ChessBoard;
import domain.Command;
import domain.Position;
import domain.Turn;
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
        Command command = inputView.readInitCommand();

        if (command == Command.END) {
            return;
        }

        ChessBoard chessBoard = new ChessBoard();
        chessBoard.init();
        outputView.printChessBoard(chessBoard);

        Turn turn = new Turn();
        while (inputView.readMoveCommand() == Command.MOVE) {
            Position current = inputView.readPosition();
            Position target = inputView.readPosition();
            inputView.readNextLine();

            turn.check(chessBoard, current);

            chessBoard.move(current, target);
            outputView.printChessBoard(chessBoard);

            turn.end();
        }
    }
}
