package chess.controller.command;

import chess.controller.ChessController;

import java.util.List;

public class End extends Executer {

    private static final int END_COMMAND_PARAMETER_COUNT = 1;

    public End(final ChessController chessController) {
        super(chessController);
    }

    public static End of(final ChessController chessController, final List<String> inputCommand) {
        validateEndCommand(inputCommand);
        return new End(chessController);
    }

    private static void validateEndCommand(List<String> inputCommand) {
        if (inputCommand.size() != END_COMMAND_PARAMETER_COUNT) {
            throw new IllegalArgumentException("유효하지 않은 end 명령어 입니다.");
        }
    }

    @Override
    public void execute() {
        chessController.end();
    }

    @Override
    public boolean isEnd() {
        return true;
    }
}
