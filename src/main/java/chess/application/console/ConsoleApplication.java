package chess.application.console;

import chess.command.Command;
import chess.command.Move;
import chess.command.Type;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleApplication {
    public static void main(String[] args) {
        OutputView outputView = new OutputView();
        outputView.printStartMessage();

        InputView inputView = new InputView();
        GameController gameController = new GameController();

        Command command;
        do {
            command = inputView.readCommand();
            executeCommand(gameController, command, outputView);
        } while (!command.isType(Type.END) && !gameController.isGameFinished());
        printResultIfGameIsFinished(gameController, outputView);

    }

    private static void executeCommand(GameController gameController, Command command, OutputView outputView) {
        if (command.isType(Type.START)) {
            outputView.printBoard(gameController.start());
        }
        if (command.isType(Type.MOVE)) {
            Move move = (Move) command;
            outputView.printBoard(gameController.move(move.getSourcePosition(), move.getTargetPosition()));
        }
        if (command.isType(Type.STATUS)) {
            outputView.printScore(gameController.status());
        }
        if (command.isType(Type.END)) {
            gameController.end();
        }
    }

    private static void printResultIfGameIsFinished(GameController gameController, OutputView outputView) {
        if (gameController.isGameFinished()) {
            outputView.printResult(gameController.status(), gameController.getWinner());
        }
    }
}
