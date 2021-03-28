package chess.controller;

import chess.domain.command.Command;
import chess.domain.game.Board;
import chess.domain.game.BoardFactory;
import chess.domain.game.Game;
import chess.domain.position.Position;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class ChessController {
    public void run() {
        if (willPlayGame()) {
            Board board = BoardFactory.create();
            Game game = new Game(board);
            OutputView.printWayToMove();
            OutputView.display(game.allBoard());

            play(game);
            printScoreIfWanted(game);
        }
    }

    private boolean willPlayGame() {
        try {
            OutputView.printWillPlayGameMessage();
            return new Command(InputView.inputCommand()).isStart();
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return willPlayGame();
        }
    }

    private void play(Game game) {
        while (game.isNotEnd()) {
            playOneTurn(game);
        }
    }

    private void playOneTurn(Game game) {
        try {
            tryOneTurn(game);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            playOneTurn(game);
        }
    }

    private void tryOneTurn(Game game) {
        OutputView.printCurrentPlayer(game.currentPlayer());
        Command command = new Command(InputView.inputCommand());
        if (command.isMove()) {
            List<String> positions = command.getOptions();
            game.move(Position.from(positions.get(0)), Position.from(positions.get(1)));
            OutputView.display(game.allBoard());
            return;
        }
        throw new IllegalArgumentException();
    }

    private void printScoreIfWanted(Game game) {
        if (willWatchScore()) {
            OutputView.printScore(game.score());
        }
    }

    private boolean willWatchScore() {
        try {
            return new Command(InputView.inputCommand()).isStatus();
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return willWatchScore();
        }
    }
}
