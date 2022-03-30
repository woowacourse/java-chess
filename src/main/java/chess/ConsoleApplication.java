package chess;

import chess.domain.command.Command;
import chess.domain.command.Type;
import chess.domain.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleApplication {
    public static void main(String[] args) {
        OutputView outputView = new OutputView();
        outputView.printStartMessage();

        ChessGame chessGame = new ChessGame();
        InputView inputView = new InputView();
        Command command;
        do {
            command = inputView.readCommand();
            command.execute(chessGame);
            printResult(command, chessGame, outputView);
        } while (!command.isType(Type.END) && !chessGame.isFinished());
    }

    private static void printResult(Command command, ChessGame chessGame, OutputView outputView) {
        if (command.isType(Type.START)) {
            outputView.printBoard(chessGame.getBoard().getSquares());
        }
        if (command.isType(Type.MOVE)) {
            printMoveResult(chessGame, outputView);
        }
        if (command.isType(Type.STATUS)) {
            outputView.printScore(chessGame.scoreOfWhite(), chessGame.scoreOfBlack());
        }
        if (command.isType(Type.END)) {
            printGameResult(chessGame, outputView);
        }
    }

    private static void printMoveResult(ChessGame chessGame, OutputView outputView) {
        outputView.printBoard(chessGame.getBoard().getSquares());
        if (chessGame.isFinished()) {
            outputView.printScore(chessGame.scoreOfWhite(), chessGame.scoreOfBlack());
            outputView.printWinner(chessGame.getWinner());
        }
    }

    private static void printGameResult(ChessGame chessGame, OutputView outputView) {
        if (chessGame.isFinished()) {
            outputView.printFinishMessage();
            outputView.printScore(chessGame.scoreOfWhite(), chessGame.scoreOfBlack());
            outputView.printWinner(chessGame.getWinner());
        }
    }
}
