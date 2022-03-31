package chess;

import chess.domain.ChessGame;
import chess.domain.command.Command;
import chess.domain.command.MoveCommand;
import chess.dto.StatusDto;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {

    public static void main(String[] args) {
        OutputView.printGameStartMessage();
        Command command = getFirstCommand();
        if (command == Command.START) {
            startGame();
        }
    }

    private static Command getFirstCommand() {
        try {
            return Command.of(InputView.getFirstCommand());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return getFirstCommand();
        }
    }

    private static void startGame() {
        ChessGame game = new ChessGame();
        OutputView.printBoard(game.getPieces());
        while (game.isRunning()) {
            OutputView.printPlayingCommandMessage();
            String commandValue = InputView.getCommand();
            Command command = Command.of(commandValue);
            if (command == Command.MOVE) {
                playTurn(commandValue, game);
            }
            if (command == Command.STATUS) {
                showStatus(game);
            }
            if (command == Command.END) {
                return;
            }
        }
    }

    private static void playTurn(String command, ChessGame game) {
        try {
            game.proceedWith(MoveCommand.of(command));
            OutputView.printBoard(game.getPieces());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    private static void showStatus(ChessGame game) {
        OutputView.printStatus(new StatusDto(game.calculateScore()));
    }
}
