package chess;

import chess.domain.ChessGame;
import chess.domain.command.Command;
import chess.domain.command.GameCommand;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

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
            execute(chessGame, command);
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception);
            executeCommand(chessGame);
        }
    }

    public static void execute(ChessGame chessGame, Command command) {
        executeStart(chessGame, command);
        executeMove(chessGame, command);
        executeStatus(chessGame, command);
        executeEnd(chessGame, command);
    }

    private static void executeStart(ChessGame chessGame, Command command) {
        if (GameCommand.isStart(command.getGameCommand())) {
            chessGame.start();
            OutputView.printChessBoard(chessGame.getBoard());
        }
    }

    private static void executeMove(ChessGame chessGame, Command command) {
        if (GameCommand.isMove(command.getGameCommand())) {
            chessGame.move(command.getSourceLocation(), command.getTargetLocation());
            OutputView.printChessBoard(chessGame.getBoard());
        }
    }

    private static void executeStatus(ChessGame chessGame, Command command) {
        if (GameCommand.isStatus(command.getGameCommand())) {
            chessGame.status();
            OutputView.printScore(chessGame.getScore());
        }
    }

    private static void executeEnd(ChessGame chessGame, Command command) {
        if (GameCommand.isEnd(command.getGameCommand())) {
            chessGame.end();
        }
    }
}
