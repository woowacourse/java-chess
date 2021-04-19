package chess.controller.console;

import chess.domain.game.BoardFactory;
import chess.domain.game.Command;
import chess.domain.game.Game;
import chess.domain.location.Position;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.Objects;

public class ConsoleChessController {
    private Game game;

    public void action(String command) {
        Command.from(command)
               .action(this);
    }

    public void init() {
        game = new Game(BoardFactory.create());
    }

    public void move(String from, String to) {
        game.move(Position.from(from), Position.from(to));
    }

    public void end() {
    }

    public void run() {
        askPlayGame();
        if (willNotPlay()) {
            return;
        }
        play();
        printScoreIfWanted();
    }

    private void askPlayGame() {
        try {
            OutputView.printStartMessage();
            action(InputView.inputCommand());
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            askPlayGame();
        }
    }

    private boolean willNotPlay() {
        return Objects.isNull(game);
    }

    private void play() {
        while (game.isNotEnd()) {
            playOneTurn();
        }
    }

    private void playOneTurn() {
        try {
            tryOneTurn();
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            playOneTurn();
        }
    }

    private void tryOneTurn() {
        OutputView.display(game.allBoard());
        OutputView.printCurrentPlayer(game.currentPlayer());
        action(InputView.inputCommand());
    }

    private void printScoreIfWanted() {
        OutputView.printWillWatchStatusMessage();
        action(InputView.inputCommand());
    }

    public void status() {
        OutputView.printScore(game.score());
    }
}
