package controller;

import static command.MoveCommand.END;

import command.MoveCommandParser;
import command.StartCommand;
import domain.board.Board;
import java.util.List;
import view.InputView;
import view.OutputView;

public final class ChessController {


    public final Board board;

    public ChessController(final Board board) {
        this.board = board;
    }

    public void run() {
        StartCommand startCommand = inputStartCommand();
        if (StartCommand.START.equals(startCommand)) {
            OutputView.printBoard(board.getPieces());
            play();
        }
    }

    private StartCommand inputStartCommand() {
        String command;
        while (validateInputStartCommand(command = InputView.readStartGameOption())) {
        }
        return StartCommand.from(command);
    }

    private boolean validateInputStartCommand(String command) {
        try {
            StartCommand.from(command);
            return false;
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return true;
        }
    }

    private void play() {
        boolean nextStep;
        do {
            nextStep = executeCommand();
        } while (nextStep);
    }

    private boolean executeCommand() {
        MoveCommandParser command = readMoveCommand();
        if (END.equals(command.getMoveCommand())) {
            return false;
        }
        movePieceWithHandling(board, command);
        OutputView.printBoard(board.getPieces());
        return true;
    }

    private void movePieceWithHandling(final Board board, final MoveCommandParser command) {
        try {
            board.move(command.getSource(), command.getDestination());
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
        }
    }

    private MoveCommandParser readMoveCommand() {
        List<String> commands;
        while (validateInputMoveCommand(commands = InputView.readPlayGameOption())) {
        }
        return MoveCommandParser.parse(commands);
    }

    private boolean validateInputMoveCommand(List<String> commands) {
        try {
            MoveCommandParser.parse(commands);
            return false;
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return true;
        }
    }
}
