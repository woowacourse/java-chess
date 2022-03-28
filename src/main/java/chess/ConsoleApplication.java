package chess;

import chess.command.Command;
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
            command.execute(chessGame, outputView);
        } while (!command.isEnd() && !chessGame.isFinished());
    }
}
