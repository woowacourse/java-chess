package controller;

import static controller.ProgressCommand.END;
import static controller.ProgressCommand.MOVE;

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

    public void run(Board board) {
        if (isGameStart()) {
            OutputView.printBoard(board.getPieces());
            play(board);
        }
    }

    private boolean isGameStart() {
        try {
            final StartCommand command = StartCommand.from(InputView.readStartGameOption());
            return StartCommand.START.equals(command);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return isGameStart();
        }
    }

    private void play(final Board board) {
        List<String> commands;
        while (MOVE.equals(ProgressCommand.from((commands = readProgressCommand()).get(COMMAND_INDEX)))) {
            board.move(getSourcePiece(commands), getDestination(commands));
            OutputView.printBoard(board.getPieces());
        }
    }

    private List<String> readProgressCommand() {
        try {
            List<String> commands = inputMoveEndCommand();
            validateCommands(commands);
            return commands;
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return readProgressCommand();
        }
    }

    private List<String> inputMoveEndCommand() {
        final List<String> gameOption = InputView.readPlayGameOption();
        ProgressCommand.from(gameOption.get(COMMAND_INDEX));
        return gameOption;
    }

    private void validateCommands(List<String> commands) {
        if (END.equals(ProgressCommand.from(commands.get(COMMAND_INDEX))) && commands.size() == 1) {
            return;
        }
        if (MOVE.equals(ProgressCommand.from(commands.get(COMMAND_INDEX))) && commands.size() == 3) {
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
