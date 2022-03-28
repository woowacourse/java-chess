package chess;

import chess.domain.ChessGame;
import chess.domain.command.Command;
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
            Command command = new Command(List.of(inputValue.split(" ")));
            chessGame.execute(command);
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception);
            playEachTurn(chessGame);
        }
    }
}
