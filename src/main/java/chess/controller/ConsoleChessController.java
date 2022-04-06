package chess.controller;

import chess.domain.game.ChessGame;
import chess.domain.game.StatusCalculator;
import chess.function.Function;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ConsoleChessController {

    public static final int COMMAND_INDEX = 0;

    public void run() {
        OutputView.printGameStart();
        ChessGame chessGame = new ChessGame();

        while (!chessGame.isFinished()) {
            play(chessGame);
        }
    }

    private void play(ChessGame chessGame) {
        try {
            List<String> commands = InputView.inputGameFunction();
            Function function = Function.from(commands.get(COMMAND_INDEX));
            execute(chessGame, function, commands);
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    private void execute(ChessGame chessGame, Function function, List<String> commands) {
        if (function.isNotStatus()) {
            function.doFunction(chessGame, commands);
            OutputView.printBoard(chessGame.getBoard().getValue());
            return;
        }
        StatusCalculator statusCalculator = chessGame.status();
        OutputView.printStatus(statusCalculator.createStatus());
    }
}
