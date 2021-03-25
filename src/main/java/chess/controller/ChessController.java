package chess.controller;

import chess.domain.game.Game;
import chess.domain.game.GameResult;
import chess.domain.position.Position;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class ChessController {
    private static final int FROM_POSITION_INDEX = 0;
    private static final int TO_POSITION_INDEX = 1;

    public void run() {
        if (willNotPlayGame()) {
            return;
        }
        final Game game = new Game();
        OutputView.printWayToMove();
        OutputView.display(game.getPieces());

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

    private void play(final Game game) {
        while (game.isNotEnd()) {
            playOneTurn(game);
        }
    }

    private void playOneTurn(final Game game) {
        try {
            tryOneTurn(game);
        } catch (Exception e) {
            OutputView.printError(e.getMessage());
            playOneTurn(game);
        }
    }

    private void tryOneTurn(final Game game) {
        OutputView.printCurrentPlayer(game.currentPlayer());
        final List<String> positions = InputView.requestPositions();
        final String from = positions.get(FROM_POSITION_INDEX);
        final String to = positions.get(TO_POSITION_INDEX);
        game.move(Position.from(from), Position.from(to));
        OutputView.display(game.getPieces());
    }

    private void printScoreIfWanted(final Game game) {
        if (willWatchScore()) {
            GameResult gameResult = new GameResult(game.getPieces());
            OutputView.printScore(gameResult.getScore());
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
