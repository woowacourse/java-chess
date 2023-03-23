package controller;

import static command.MoveCommand.END;
import static command.MoveCommand.MOVE;

import command.MoveCommand;
import command.StartCommand;
import domain.board.Board;
import domain.position.Position;
import domain.position.Positions;
import java.util.List;
import view.InputView;
import view.OutputView;

public final class ChessController {

    private static final int COMMAND_INDEX = 0;
    private static final int SOURCE_PIECE = 1;
    private static final int DESTINATION = 2;

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
            System.out.println(command);
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
        List<String> commands = readProgressCommand();
        MoveCommand command = MoveCommand.from(commands.get(COMMAND_INDEX));
        if (END.equals(command)) {
            return false;
        }
        movePieceWithHandling(board, commands);
        OutputView.printBoard(board.getPieces());
        return true;
    }

    private void movePieceWithHandling(final Board board, final List<String> commands) {
        try {
            board.move(getSourcePiece(commands), getDestination(commands));
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
        }
    }

    private List<String> readProgressCommand() {
        try {
            List<String> commands = inputMoveEndCommand();
            validateMoveCommands(commands);
            return commands;
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return readProgressCommand();
        }
    }

    private List<String> inputMoveEndCommand() {
        final List<String> gameOption = InputView.readPlayGameOption();
        MoveCommand.from(gameOption.get(COMMAND_INDEX));
        return gameOption;
    }

    private void validateMoveCommands(final List<String> commands) {
        if (END.equals(MoveCommand.from(commands.get(COMMAND_INDEX))) && commands.size() == 1) {
            return;
        }
        if (MOVE.equals(MoveCommand.from(commands.get(COMMAND_INDEX))) && commands.size() == 3) {
            return;
        }

        throw new IllegalArgumentException("move source위치 target위치 또는 end로 입력해야 합니다.");
    }

    private Position getDestination(final List<String> gameOption) {
        return Positions.from(gameOption.get(DESTINATION));
    }

    private Position getSourcePiece(final List<String> gameOption) {
        return Positions.from(gameOption.get(SOURCE_PIECE));
    }
}
