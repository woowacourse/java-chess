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
        Command.from(InputView.inputCommand()).action(game);
        if (game.isFinished()) {
            return;
        }

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
        Command.from(InputView.inputCommand()).action(game);
        OutputView.display(game.allBoard());
    }

    private void printScoreIfWanted(Game game) {
        if (game.isStatus()) {
            OutputView.printScore(game.score());
        }
    }
}
