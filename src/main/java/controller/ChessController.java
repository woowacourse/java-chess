package controller;

import static view.InputView.*;
import static view.InputView.responseUserCommand;
import static view.InputView.responseUserStartCommand;

import domain.ChessBoard;
import domain.ChessBoardGenerator;
import domain.position.Position;
import view.OutputView;

public class ChessController {

    public static final int SOURCE_INDEX = 0;
    public static final int TARGET_INDEX = 1;
    public static final int FIRST_SINGLE_LETTER = 0;
    public static final int SECOND_SINGLE_LETTER = 1;

    public void start() {
        ChessBoard chessBoard = new ChessBoard(new ChessBoardGenerator());
        String input = null;

        input = playStart(input);
        if (input.equals(END)) {
            return;
        }
        if (input.equals(START)) {
            OutputView.printBoard(chessBoard);
            playTurn(chessBoard);
        }
    }

    private String playStart(String input) {
        try {
            return responseUserStartCommand();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return playStart(input);
        }
    }

    private void playTurn(ChessBoard chessBoard) {
        try {
            interactUser(chessBoard);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            playTurn(chessBoard);
        }
    }

    private void interactUser(ChessBoard chessBoard) {
        String input = responseUserCommand();

        while (!input.equals(END)) {
            String[] positions = input.split(DELIMITER);
            Position source = generatePosition(positions[SOURCE_INDEX]);
            Position target = generatePosition(positions[TARGET_INDEX]);
            chessBoard.move(source, target);

            OutputView.printBoard(chessBoard);
            input = responseUserCommand();
        }
    }

    private Position generatePosition(String value) {
        return Position.of(extractSingleLetter(value, FIRST_SINGLE_LETTER),
                extractSingleLetter(value, SECOND_SINGLE_LETTER));
    }

    private String extractSingleLetter(String value, int index) {
        return value.substring(index, index + 1);
    }
}
