package chess.controller.console;


import chess.domain.board.Position;
import chess.domain.game.ChessGame;
import chess.domain.state.Ready;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleGameController {

    public void run() {
        final ChessGame chessGame = new ChessGame(0L, new Ready());
        OutputView.printInitMessage();

        play(chessGame);
    }

    private String inputCommand() {
        try {
            return Command.from(InputView.inputCommend());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputCommand();
        }
    }

    private void play(final ChessGame chessGame) {
        while (!chessGame.isFinished()) {
            executeCommand(chessGame, inputCommand());
        }
        if (chessGame.isFinished()) {
            commandEnd(chessGame);
        }
    }

    private void executeCommand(ChessGame chessGame, String commands) {
        String[] command_element = commands.split(" ");
        String command = command_element[0];
        if (command.equals("start")) {
            commandStart(chessGame);
        }
        if (command.equals("move")) {
            commandMove(chessGame, command_element[1], command_element[2]);
        }
        if (command.equals("status")) {
            commandStatus(chessGame);
        }
        if (command.equals("end")) {
            chessGame.end();
        }
    }

    private void commandStart(ChessGame chessGame) {
        try {
            chessGame.start();
            OutputView.printChessBoard(chessGame.board());
        } catch (IllegalArgumentException | UnsupportedOperationException e) {
            OutputView.printErrorMessage(e.getMessage());
            play(chessGame);
        }
    }

    private void commandMove(ChessGame chessGame, String from, String to) {
        try {
            chessGame.move(Position.valueOf(from), Position.valueOf(to));
            OutputView.printChessBoard(chessGame.board());
        } catch (IllegalArgumentException | UnsupportedOperationException e) {
            OutputView.printErrorMessage(e.getMessage());
            play(chessGame);
        }
    }

    private void commandStatus(ChessGame chessGame) {
        try {
            OutputView.printStatus(chessGame.status());
        } catch (IllegalArgumentException | UnsupportedOperationException e) {
            OutputView.printErrorMessage(e.getMessage());
            play(chessGame);
        }
    }

    private void commandEnd(ChessGame chessGame) {
        try {
            chessGame.end();
            OutputView.printGameResult(chessGame.status());
        } catch (IllegalArgumentException | UnsupportedOperationException e) {
            OutputView.printErrorMessage(e.getMessage());
            play(chessGame);
        }
    }
}
