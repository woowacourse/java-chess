package chess;

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
        Controller controller = new Controller();

        Command command;
        do {
            command = inputView.readCommand();
            executeCommand(controller, command, outputView);
        } while (!command.isType(Type.END) && !controller.isGameFinished());
    }

    private static void executeCommand(Controller controller,
                                       Command command,
                                       OutputView outputView) {
        if (command.isType(Type.START)) {
            outputView.printBoard(controller.start());
        }
        if (command.isType(Type.MOVE)) {
            executeMove(controller, (Move) command, outputView);
        }
        if (command.isType(Type.STATUS)) {
            outputView.printScore(controller.status());
        }
        if (command.isType(Type.END)) {
            executeEnd(controller, outputView);
        }
    }

    private static void executeMove(Controller controller,
                                    Move move,
                                    OutputView outputView) {
        outputView.printBoard(controller.move(move.getSourcePosition(), move.getTargetPosition()));
        if (controller.isGameFinished()) {
            outputView.printResult(controller.status(), controller.getWinner());
        }
    }

    private static void executeEnd(Controller controller,
                                   OutputView outputView) {
        controller.end();
        if (controller.isGameFinished()) {
            outputView.printResult(controller.status(), controller.getWinner());
        }
    }
}
