package chess.controller;

import chess.domain.ChessRunner;
import chess.domain.piece.Team;
import chess.view.ConsoleInputView;
import chess.view.ConsoleOutputView;
import chess.view.InputView;
import chess.view.OutputView;
import org.apache.commons.lang3.StringUtils;

public class ChessController {
    private static InputView inputView = new ConsoleInputView();
    private static OutputView outputView = new ConsoleOutputView();

    public static void start() {
        try {
            runChessGame();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            start();
        }
    }

    public static void runChessGame() {
        Command command = Command.of(inputView.askChessRun());

        if (command.isStart()) {
            ChessRunner chessRunner = new ChessRunner();
            GameController startController = command.getGameController();
            startController.execute(chessRunner, StringUtils.EMPTY);

            do {
                String commands = inputView.askGameCommand();
                command = Command.of(commands);
                GameController gameController = command.getGameController();
                gameController.execute(chessRunner, commands);
            } while (!command.isEnd() && findWinner(chessRunner));
        }
    }

    private static boolean findWinner(final ChessRunner chessRunner) {
        Team winner = chessRunner.findWinner();
        if (winner != null) {
            outputView.printWinner(winner);
            return false;
        }
        return true;
    }
}
