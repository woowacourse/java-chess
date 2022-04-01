package chess;

import chess.domain.board.Position;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

public final class ConsoleApplication {
    public static void main(String[] args) {
        OutputView.startMessage();
        final ConsoleChessGame consoleChessGame = new ConsoleChessGame();

        Command command = Command.from(InputView.scanCommand());
        while (!command.isEnd()) {
            if (command.isStart()) {
                OutputView.printBoard(consoleChessGame.board());
            }

            if (command.isMove()) {
                consoleChessGame.move(Position.from(command.getFrom()), Position.from(command.getTo()));
                OutputView.printBoard(consoleChessGame.board());
            }

            if (command.isStatus()) {
                OutputView.printScore(consoleChessGame.score());
            }

            if (consoleChessGame.isFinished()) {
                OutputView.printBoard(consoleChessGame.board());
                OutputView.printScore(consoleChessGame.score());
                OutputView.printFinished();
            }

            command = Command.from(InputView.scanCommand());
        }
    }
}
