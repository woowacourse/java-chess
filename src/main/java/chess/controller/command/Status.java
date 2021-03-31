package chess.controller.command;

import chess.controller.ChessController;

import java.util.List;

public class Status extends Executer {

    private static final int STATUS_COMMAND_PARAMETER_COUNT = 1;

    public Status(final ChessController chessController) {
        super(chessController);
    }

    public static Status of(final ChessController chessController, final List<String> inputCommand) {
        validateStatusCommand(inputCommand);
        return new Status(chessController);
    }

    private static void validateStatusCommand(List<String> inputCommand) {
        if (inputCommand.size() != STATUS_COMMAND_PARAMETER_COUNT) {
            throw new IllegalArgumentException("유효하지 않은 status 명령어 입니다.");
        }
    }

    @Override
    public void execute() {
        chessController.status();
    }
}
