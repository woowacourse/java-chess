package chess.controller;

import chess.domain.game.Command;
import chess.domain.game.Board;
import chess.domain.game.BoardFactory;
import chess.domain.game.Game;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    public void run() {
        Board board = BoardFactory.create();
        Game game = new Game(board);
        OutputView.printWillPlayGameMessage();
        game.action(Command.from(InputView.inputCommand()));

        OutputView.printWayToMove();
        OutputView.display(game.allBoard());

        play(game);
        printScoreIfWanted(game);
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
        game.action(Command.from(InputView.inputCommand()));
        OutputView.display(game.allBoard());
    }

    private void printScoreIfWanted(Game game) {
        if (willWatchScore()) {
            OutputView.printScore(game.score());
        }
    }

    private boolean willWatchScore() {
        try {
            return Command.from(InputView.inputCommand()) == Command.STATUS;
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return willWatchScore();
        }
    }
}
