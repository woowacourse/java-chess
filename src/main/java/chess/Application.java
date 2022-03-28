package chess;

import chess.domain.Board;
import chess.domain.ChessGame;
import chess.domain.command.Command;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;
import java.util.function.Consumer;

public class Application {

    public static void main(String[] args) {
        OutputView.printChessCommandInfo();
        ChessGame chessGame = new ChessGame();
        do {
            executeCommand(chessGame);
        } while (chessGame.isRunning());
    }

    private static void executeCommand(ChessGame chessGame) {
        try {
            String inputValue = InputView.askCommand();
            Command command = new Command(List.of(inputValue.split(" ")));
            Consumer<Board> execute = chessGame.execute(command);
            execute.accept(chessGame.getBoard());
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception);
            executeCommand(chessGame);
        }
    }
}
