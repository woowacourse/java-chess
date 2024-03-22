package controller;

import dto.GameBoardDto;
import model.GameBoard;
import model.status.GameStatus;
import model.status.Initialization;
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
        gameBoard.setting();
        outputView.printStartMessage();
        play(gameBoard);
    }

    private void play(final GameBoard gameBoard) {
        GameStatus gameStatus = Initialization.gameSetting(inputController.getCommand());

        while (gameStatus.isRunning()) {
            printCurrentStatus(gameBoard);
            gameStatus = gameStatus.play(inputController.getCommand(), gameBoard);
        }
    }

    private void printCurrentStatus(final GameBoard gameBoard) {
        outputView.printGameBoard(GameBoardDto.from(gameBoard));
        outputView.printCurrentCamp(gameBoard.getCamp()); // TODO next가 맞을까 cur이 맞을까
    }
}
