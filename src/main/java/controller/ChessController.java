package controller;

import domain.board.ChessGame;
import domain.piece.Coordinate;
import view.InputView;
import view.OutputView;

import java.util.List;

public final class ChessController {

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
        ChessGame chessGame = new ChessGame();
        Command command = Command.of(inputView.readCommand());
        if (command.isStart()) {
            startInteractionLoop(chessGame);
        }
        outputView.printGameEndMessage();
    }

    private void startInteractionLoop(final ChessGame chessGame) {
        try {
            doOneInteraction(chessGame);
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e.getMessage());
            startInteractionLoop(chessGame);
        }
    }

    private void doOneInteraction(final ChessGame chessGame) {
        Command command;
        do {
            outputView.printBoard(chessGame);
            List<String> frontCommand = inputView.readCommand();
            command = Command.of(frontCommand);
            moveByCommand(chessGame, command, frontCommand);
        } while (command.isNotEnd());
    }

    private void moveByCommand(final ChessGame chessGame, final Command command, final List<String> frontCommand) {
        if (command.isMove()) {
            Coordinate startCoordinate = CoordinateAdapter.convert(frontCommand.get(START_COORDINATE_INDEX));
            Coordinate endCoordinate = CoordinateAdapter.convert(frontCommand.get(END_COORDINATE_INDEX));
            chessGame.move(startCoordinate, endCoordinate);
        }
    }
}
