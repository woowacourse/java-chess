package chess;

import chess.command.Command;
import chess.command.Move;
import chess.command.Type;
import chess.domain.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleApplication {
    public static void main(String[] args) {
        OutputView outputView = new OutputView();
        outputView.printStartMessage();

        InputView inputView = new InputView();
        ChessGame chessGame = new ChessGame();
        Controller controller = new Controller();

        Command command;
        do {
            command = inputView.readCommand();
            executeCommand(outputView, chessGame, controller, command);
        } while (!command.isType(Type.END) && !chessGame.isFinished());
    }

    private static void executeCommand(OutputView outputView, ChessGame chessGame, Controller controller, Command command) {
        if (command.isType(Type.START)) {
            outputView.printBoard(controller.start(chessGame));
        }
        if (command.isType(Type.MOVE)) {
            executeMove(outputView, chessGame, controller, (Move) command);
        }
        if (command.isType(Type.STATUS)) {
            outputView.printScore(controller.status(chessGame));
        }
        if (command.isType(Type.END)) {
            executeEnd(outputView, chessGame, controller);
        }
    }

    private static void executeMove(OutputView outputView, ChessGame chessGame, Controller controller, Move move) {
        outputView.printBoard(controller.move(chessGame, move.getSourcePosition(), move.getTargetPosition()));
        if (chessGame.isFinished()) {
            outputView.printResult(controller.status(chessGame), controller.getWinner(chessGame));
        }
    }

    private static void executeEnd(OutputView outputView, ChessGame chessGame, Controller controller) {
        controller.end(chessGame);
        if (chessGame.isFinished()) {
            outputView.printResult(controller.status(chessGame), controller.getWinner(chessGame));
        }
    }
}
