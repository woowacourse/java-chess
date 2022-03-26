package controller;

import domain.chessboard.ChessBoard;
import domain.chessboard.ChessBoardGenerator;
import domain.position.Position;
import view.InputView;
import view.OutputView;

public class ChessController {

    public static final int FILE_INDEX = 0;
    public static final int RANK_INDEX = 1;

    public void start() {
        ChessBoard chessBoard = new ChessBoard(new ChessBoardGenerator());
        String input = InputView.startCommand();

        if (input.equals(InputView.START)) {
            OutputView.printBoard(chessBoard);
            play(chessBoard);
        }
    }

    private void play(ChessBoard chessBoard) {
        try {
            String input = InputView.playCommand();
            if (input.equals(InputView.END)) {
                OutputView.printExitMessage();
                return;
            }

            move(input, chessBoard);
            play(chessBoard);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            play(chessBoard);
        }
    }

    private void move(String input, ChessBoard chessBoard) {
        String[] positions = input.split(InputView.DELIMITER);
        Position source = generatePosition(positions[InputView.SOURCE_INDEX]);
        Position target = generatePosition(positions[InputView.TARGET_INDEX]);
        chessBoard.move(source, target);
        OutputView.printBoard(chessBoard);
    }

    private Position generatePosition(String value) {
        String[] position = value.split(InputView.EMPTY_STRING);
        return Position.of(position[FILE_INDEX], position[RANK_INDEX]);
    }
}
