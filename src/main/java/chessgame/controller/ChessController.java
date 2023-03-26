package chessgame.controller;

import java.util.Optional;

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
        } while (game.isNotEnd());
    }

    private void eachTurn(Game game) {
        Command command = readCommand();
        try {
            game.setFrom(command);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMsg(e.getMessage());
        }
        printChessBoard(command, game);
    }

    private Command readCommand() {
        Optional<Command> command;
        do {
            command = generateCommand();
        } while (command.isEmpty());
        return command.get();
    }

    private Optional<Command> generateCommand() {
        try {
            return Optional.of(Command.of(inputView.readCommand()));
        } catch (IllegalArgumentException e) {
            outputView.printErrorMsg(e.getMessage());
            return Optional.empty();
        }
    }

    private void printChessBoard(Command command, Game game) {
        if (game.isRunning()) {
            outputView.printChessBoard(game.board());
        }
        if (command.isStatus()) {
            outputView.printScore(game.score());
        }
        if (game.isEnd()) {
            outputView.printWinner(game.winner());
        }
    }
}
