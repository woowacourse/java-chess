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
            printResult(game);
        } while (!game.isEnd());
        if(game.isEndByKing()) {
            outputView.printWinner(game.winTeam());
        }
    }

    private void eachTurn(Game game) {
        try {
            Command command = readCommand();
            setState(game, command);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMsg(e.getMessage());
            eachTurn(game);
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

    private void setState(Game game, Command command) {
        try {
            game.setState(command);
            printStatusResult(game, command);
        } catch (UnsupportedOperationException e) {
            outputView.printErrorMsg(e.getMessage());
            setState(game, readCommand());
        }
    }

    private void printStatusResult(Game game, Command command) {
        if(command.isStatus()){
            outputView.printScore(game.scoreBoard());
            outputView.printScoreWinner(game.scoreBoard());
        }
    }

    private void printResult(Game game) {
        if (!game.isEnd()) {
            outputView.printChessBoard(game.board());
        }
    }
}
