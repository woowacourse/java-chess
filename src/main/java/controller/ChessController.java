package controller;

import domain.chessboard.ChessBoard;
import domain.chessboard.ChessBoardGenerator;
import domain.dto.StatusDTO;
import domain.position.Position;
import view.InputView;
import view.OutputView;

public class ChessController {

    public static final int FILE_INDEX = 0;
    public static final int RANK_INDEX = 1;

    public void start() {
        ChessBoard chessBoard = new ChessBoard(new ChessBoardGenerator());
        String input = validateStartCommand();

        if (input.equals(InputView.START)) {
            OutputView.printBoard(chessBoard);
            play(chessBoard);
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

    private void play(ChessBoard chessBoard) {
        try {
            playTurn(chessBoard);
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            play(chessBoard);
        }
    }

    private void playTurn(ChessBoard chessBoard) {
        String input = InputView.playCommand();
        if (isEndCommand(input)) {
            return;
        }
        status(input, chessBoard);
        move(input, chessBoard);
        if (isKingOnlyOne(chessBoard)) {
            return;
        }
        play(chessBoard);
    }

    private boolean isKingOnlyOne(ChessBoard chessBoard) {
        if (chessBoard.isKingOnlyOne()) {
            OutputView.printAttackKingMessage(chessBoard.getCurrentPlayer());
            return true;
        }
        return false;
    }

    private boolean isEndCommand(final String command) {
        if (command.equals(InputView.END)) {
            OutputView.printExitMessage();
            return true;
        }
        return false;
    }

    private void status(final String input, ChessBoard chessBoard) {
        if (input.equals(InputView.STATUS)) {
            OutputView.printStatus(new StatusDTO(chessBoard));
            OutputView.printBoard(chessBoard);
        }
    }

    private void move(final String input, ChessBoard chessBoard) {
        if (!input.equals(InputView.STATUS)) {
            String[] positions = input.split(InputView.DELIMITER);
            Position source = generatePosition(positions[InputView.SOURCE_INDEX]);
            Position target = generatePosition(positions[InputView.TARGET_INDEX]);
            chessBoard.move(source, target);
            OutputView.printBoard(chessBoard);
        }
    }

    private Position generatePosition(final String value) {
        String[] position = value.split(InputView.EMPTY_STRING);
        return Position.of(position[FILE_INDEX], position[RANK_INDEX]);
    }
}
