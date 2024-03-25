package controller;

import dto.GameBoardDto;
import model.Camp;
import model.ChessGame;
import model.Command;
import model.menu.Menu;
import view.InputView;
import view.OutputView;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        ChessGame chessGame = new ChessGame();
        outputView.printStartMessage();
        play(chessGame);
    }

    private void play(final ChessGame chessGame) {
        Menu status = Command.of(inputView.readCommandList());
        status.play(chessGame);
        while (chessGame.isNotEnd()) {
            printCurrentStatus(chessGame, chessGame.getCamp());
            status = Command.of(inputView.readCommandList());
            status.play(chessGame);
        }
    }

    private void printCurrentStatus(final ChessGame chessGame, final Camp camp) {
        outputView.printGameBoard(GameBoardDto.from(chessGame));
        outputView.printCurrentCame(camp);
    }
}
