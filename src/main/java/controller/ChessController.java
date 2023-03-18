package controller;

import domain.board.Board;
import domain.piece.Coordinate;
import view.InputView;
import view.OutputView;

import java.util.List;

public class ChessController {

    public static final char ASCII_ALPHABET_A = 'a';
    public static final int START_COORDINATE_INDEX = 1;
    public static final int END_COORDINATE_INDEX = 2;

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        try {
            startChessGame();
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e.getMessage());
        }
    }

    private void startChessGame() {
        outputView.printGameStartMessage();
        Board board = new Board();
        Command command = Command.of(inputView.readCommand());
        if (command.isStart()) {
            startBoardInteraction(board);
        }
        outputView.printGameEndMessage();
    }

    private void startBoardInteraction(final Board board) {
        Command command;
        do {
            outputView.printBoard(board);
            List<String> frontCommand = inputView.readCommand();
            command = Command.of(frontCommand);
            moveByCommand(board, command, frontCommand);
        } while (command.isNotEnd());
    }

    private void moveByCommand(final Board board, final Command command, final List<String> frontCommand) {
        if (command.isMove()) {
            Coordinate startCoordinate = convertCoordinate(frontCommand.get(START_COORDINATE_INDEX));
            Coordinate endCoordinate = convertCoordinate(frontCommand.get(END_COORDINATE_INDEX));
            board.move(startCoordinate, endCoordinate);
        }
    }

    private Coordinate convertCoordinate(final String frontCoordinate) {
        int row = Character.getNumericValue(frontCoordinate.charAt(1)) - 1;
        int col = (int) frontCoordinate.charAt(0) - ASCII_ALPHABET_A;
        return new Coordinate(row, col);
    }
}
