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
            executeCommand(controller, command, chessGame, outputView);
        } while (!command.isType(Type.END) && !chessGame.isFinished());
    }

    private static void executeCommand(Controller controller,
                                       Command command,
                                       ChessGame chessGame,
                                       OutputView outputView) {
        if (command.isType(Type.START)) {
            outputView.printBoard(controller.start(chessGame));
        }
        if (command.isType(Type.MOVE)) {
            executeMove(controller, (Move) command, chessGame, outputView);
        }
        if (command.isType(Type.STATUS)) {
            outputView.printScore(controller.status(chessGame));
        }
        if (command.isType(Type.END)) {
            executeEnd(controller, chessGame, outputView);
        }
    }

    private static void executeMove(Controller controller,
                                    Move move,
                                    ChessGame chessGame,
                                    OutputView outputView) {
        outputView.printBoard(controller.move(chessGame, move.getSourcePosition(), move.getTargetPosition()));
        if (chessGame.isFinished()) {
            outputView.printResult(controller.status(chessGame), controller.getWinner(chessGame));
        }
    }

    private static void executeEnd(Controller controller,
                                   ChessGame chessGame,
                                   OutputView outputView) {
        controller.end(chessGame);
        if (chessGame.isFinished()) {
            outputView.printResult(controller.status(chessGame), controller.getWinner(chessGame));
        }
    }
}
