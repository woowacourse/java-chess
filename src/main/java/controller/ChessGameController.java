package controller;

import domain.ChessGame;
import domain.chessboard.ChessBoard;
import domain.coordinate.MovePosition;
import domain.coordinate.Position;
import domain.coordinate.PositionFactory;
import view.Command;
import view.InputView;
import view.OutputView;

import java.util.List;

public class ChessGameController {

    private static final int COMMAND_INDEX = 0;
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;

    private ChessGame chessGame;
    private boolean isKeepGaming;

    public ChessGameController() {
    }

    public void run() {
        OutputView.printStartMessage();

        isKeepGaming = true;

        while (isKeepGaming) {
            progress();
        }
    }

    public void progress() {
        try {
            playTurn();
            OutputView.printChessBoard(chessGame.getChessBoard());
        } catch (IllegalStateException exception) {
            OutputView.printErrorMessage(exception.getMessage());
        }
    }

    private void playTurn() {
        List<String> inputs = InputView.readline();
        Command command = Command.from(inputs.get(COMMAND_INDEX));
        isStart(command);
        isMove(inputs, command);
        isEnd(command);
        isStatus(command);
    }

    private void isStart(final Command command) {
        if (command == Command.START) {
            chessGame = new ChessGame(ChessBoard.generate());
        }
    }

    private void isMove(final List<String> inputs, final Command command) {
        if (command == Command.MOVE) {
            Position source = PositionFactory.createPosition(inputs.get(SOURCE_INDEX));
            Position target = PositionFactory.createPosition(inputs.get(TARGET_INDEX));
            chessGame.move(MovePosition.of(source, target));
            exitIfCheckmate();
        }

    }

    private void exitIfCheckmate() {
        if (chessGame.isCheckMate()) {
            OutputView.printResult(chessGame.getCheckMateResult());
            isKeepGaming = false;
        }
    }

    private void isStatus(final Command command) {
        if (command == Command.STATUS) {
            OutputView.printStatusResult(chessGame.getStatusResult());
            isKeepGaming = false;
        }
    }

    private void isEnd(final Command command) {
        if (command == Command.END) {
            isKeepGaming = false;
        }
    }

}
