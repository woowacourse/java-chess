package chess.controller;

import chess.domain.Game;
import chess.domain.position.Position;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class ChessController {
    public void run() {
        if (willNotPlayGame()) {
            return;
        }
        Game game = new Game();
        OutputView.printWayToMove();
        game.display();

        play(game);
        printScoreIfWanted(game);
    }

    private boolean willNotPlayGame() {
        try {
            return InputView.willNotPlayGame();
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return willNotPlayGame();
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
        List<String> positions = InputView.requestPositions();
        String from = positions.get(0);
        String to = positions.get(1);
        game.move(Position.from(from), Position.from(to));
        game.display();
    }

    private void printScoreIfWanted(Game game) {
        if (willWatchScore()) {
            OutputView.printScore(game.score());
        }
    }

    private boolean willWatchScore() {
        try {
            return InputView.willWatchScore();
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return willWatchScore();
        }
    }
}
