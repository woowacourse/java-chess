package chess;

import chess.domain.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

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
            chessGame.execute(List.of(inputValue.split(" ")));
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception);
            playEachTurn(chessGame);
        }
    }
}
