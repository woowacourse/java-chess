package controller;

import dto.GameBoardDto;
import model.Camp;
import model.GameBoard;
import model.menu.ChessStatus;
import model.menu.Init;
import view.OutputView;

public class ChessController {

    private final InputController inputController;
    private final OutputView outputView;


    public ChessController(final InputController inputController, final OutputView outputView) {
        this.inputController = inputController;
        this.outputView = outputView;
    }

    public void run() {
        GameBoard gameBoard = new GameBoard();
        outputView.printStartMessage();
        play(gameBoard);
    }

    private void play(final GameBoard gameBoard) {
        ChessStatus currentChessStatus = new Init();
        while (currentChessStatus.isRunning()) {
            currentChessStatus = currentChessStatus.play(inputController.getCommand(), gameBoard);
            printCurrentStatus(gameBoard, gameBoard.getCamp());
        }
    }

    private void printCurrentStatus(final GameBoard gameBoard, final Camp camp) {
        outputView.printGameBoard(GameBoardDto.from(gameBoard));
        outputView.printCurrentCame(camp);
    }
}
