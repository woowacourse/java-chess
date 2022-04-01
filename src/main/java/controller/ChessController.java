package controller;

import domain.Status;
import domain.chessgame.ChessBoard;
import domain.chessgame.ChessGame;
import domain.position.Position;
import utils.ChessBoardGenerator;
import view.InputView;
import view.OutputView;

public class ChessController {

    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;

    public void start() {
        ChessBoard chessBoard = ChessBoardGenerator.generate();
        ChessGame chessGame = new ChessGame(chessBoard);
        String input = validateStartCommand();

        if (input.equals(InputView.START)) {
            OutputView.printBoard(chessGame.getChessBoard());
            play(chessGame);
        }
    }

    private String validateStartCommand() {
        try {
            return InputView.startCommand();
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return validateStartCommand();
        }
    }

    private void play(ChessGame chessGame) {
        try {
            playTurn(chessGame);
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            play(chessGame);
        }
    }

    private void playTurn(ChessGame chessGame) {
        String input = InputView.playCommand();
        if (isEndCommand(input)) {
            return;
        }
        status(input, chessGame);
        move(input, chessGame);
        if (chessGame.isFinished()) {
            return;
        }
        play(chessGame);
    }

    private boolean isEndCommand(final String command) {
        if (command.equals(InputView.END)) {
            OutputView.printExitMessage();
            return true;
        }
        return false;
    }

    private void move(final String input, ChessGame chessGame) {
        if (!input.equals(InputView.STATUS)) {
            String[] positions = input.split(InputView.DELIMITER);
            Position source = generatePosition(positions[InputView.SOURCE_INDEX]);
            Position target = generatePosition(positions[InputView.TARGET_INDEX]);
            chessGame.move(source, target);
            OutputView.printBoard(chessGame.getChessBoard());
        }
    }

    private void status(final String input, ChessGame chessGame) {
        if (input.equals(InputView.STATUS)) {
            Status status = chessGame.status();
            OutputView.printStatus(status);
            OutputView.printBoard(chessGame.getChessBoard());
        }
    }

    private Position generatePosition(final String value) {
        String[] position = value.split(InputView.EMPTY_STRING);
        return Position.of(position[FILE_INDEX], position[RANK_INDEX]);
    }
}
