package controller;

import static view.InputView.*;

import domain.ChessBoard;
import domain.ChessBoardGenerator;
import domain.dto.StatusDto;
import domain.position.Position;
import java.util.Arrays;
import java.util.List;
import view.InputView;
import view.OutputView;

public final class ChessController {

    public static final int SOURCE_INDEX = 0;
    public static final int TARGET_INDEX = 1;
    public static final int FIRST_SINGLE_LETTER = 0;
    public static final int SECOND_SINGLE_LETTER = 1;

    public void start() {
        ChessBoard chessBoard = new ChessBoard(new ChessBoardGenerator());
        String input = null;
        input = interactPlayStart(input);

        if (input.equals(END)) {
            return;
        }
        playStart(chessBoard, input);
    }

    private String interactPlayStart(final String input) {
        try {
            return InputView.responseUserStartCommand();
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return interactPlayStart(input);
        }
    }

    private void playStart(ChessBoard chessBoard, final String input) {
        if (input.equals(START)) {
            OutputView.printBoard(chessBoard);
            interactPlayTurn(chessBoard);
        }
    }

    private void interactPlayTurn(ChessBoard chessBoard) {
        try {
            playTurn(chessBoard);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            interactPlayTurn(chessBoard);
        }
    }

    private void playTurn(ChessBoard chessBoard) {
        final String input = responseUserCommand();
        if (input.equals(END)) {
            return;
        }
        playStatus(input, chessBoard);
        playMove(input, chessBoard);
        if (checkExistOneKing(chessBoard)) {
            return;
        }
        playTurn(chessBoard);
    }

    private void playStatus(final String input, ChessBoard chessBoard) {
        if (input.equals(STATUS)) {
            OutputView.printStatus(new StatusDto(chessBoard));
            playTurn(chessBoard);
        }
    }

    private void playMove(final String input, ChessBoard chessBoard) {
        final List<String> positions = Arrays.asList(input.split(DELIMITER));

        Position source = generatePosition(positions.get(SOURCE_INDEX));
        Position target = generatePosition(positions.get(TARGET_INDEX));
        chessBoard.move(source, target);
        OutputView.printBoard(chessBoard);
    }

    private boolean checkExistOneKing(ChessBoard chessBoard) {
        if (!chessBoard.checkKingExist()) {
            OutputView.printWinner(chessBoard.calculateWhoWinner());
            return true;
        }
        return false;
    }

    private Position generatePosition(final String value) {
        return Position.of(extractSingleLetter(value, FIRST_SINGLE_LETTER),
                extractSingleLetter(value, SECOND_SINGLE_LETTER));
    }

    private String extractSingleLetter(final String value, final int index) {
        return value.substring(index, index + 1);
    }
}
