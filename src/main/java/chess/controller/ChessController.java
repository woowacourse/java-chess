package chess.controller;

import chess.domain.game.Game;
import chess.dto.Request;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(InputView inputView, OutputView outputVIew) {
        this.inputView = inputView;
        this.outputView = outputVIew;
    }

    public void run() {
        outputView.printIntroduction();
        playGame();
    }

    private void playGame() {
        Game game = new Game();
        while (game.isRunnable()) {
            tryExecute(game);
        }
    }

    private void tryExecute(Game game) {
        try {
            execute(game);
        } catch (RuntimeException e) {
            outputView.printException(e);
            tryExecute(game);
        }
    }

    private void execute(Game game) {
        String input = inputView.inputCommand();
        Request request = Request.of(input);

        game.run(request);
        outputView.printResponse(request.getCommand(), game.getResponse());
    }
}
