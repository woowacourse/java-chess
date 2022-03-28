package chess;

import chess.domain.ChessGame;
import chess.domain.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {
    public static void main(String[] args) {
        OutputView.printChessCommandInfo();
        ChessGame chessGame = new ChessGame();
        do {
            playEachTurn(chessGame);
        } while (chessGame.isRunning());
    }

    private static void playEachTurn(ChessGame chessGame) {
        try {
            String inputValue = InputView.askCommand();
            chessGame.execute(Command.of(inputValue));
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception);
            playEachTurn(chessGame);
        }
    }
}
