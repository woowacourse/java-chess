package controller;

import dto.GameBoardDto;
import model.Camp;
import model.ChessGame;
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
        ChessGame chessGame = new ChessGame();
        outputView.printStartMessage();
        play(chessGame);
    }

    private void play(final ChessGame chessGame) {
        ChessStatus currentChessStatus = new Init();
        while (currentChessStatus.isRunning()) {
            currentChessStatus = currentChessStatus.play(inputController.getCommand(), chessGame);
            printCurrentStatus(chessGame, chessGame.getCamp());
        }
    }

    private void printCurrentStatus(final ChessGame chessGame, final Camp camp) {
        outputView.printGameBoard(GameBoardDto.from(chessGame));
        outputView.printCurrentCame(camp);
    }
}
