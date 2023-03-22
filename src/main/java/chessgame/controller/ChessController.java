package chessgame.controller;

import chessgame.domain.Command;
import chessgame.domain.Game;
import chessgame.view.InputView;
import chessgame.view.OutputView;

public class ChessController {
    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Game game = new Game();
        playGame(game);
    }

    private void playGame(Game game) {
        outputView.printStartMessage();
        do {
            eachTurn(game);
        } while (game.isRunning());
    }

    private void eachTurn(Game game) {
        Command command = readCommand();
        try {
            game.setState(command);
            printChessBoard(game);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMsg(e.getMessage());
        }
    }

    private Command readCommand() {
        try {
            return Command.of(inputView.readCommand());
        } catch (IllegalArgumentException e) {
            outputView.printErrorMsg(e.getMessage());
            return readCommand();
        }
    }

    private void printChessBoard(Game game) {
        if(game.isRunning()){
            outputView.printChessBoard(game.board());
        }
    }
}
