package chess.domain.command;

import chess.domain.manager.ChessManager;

import java.util.List;

public class End extends Executor {

    private static final int END_COMMAND_PARAMETER_COUNT = 1;

    public End(ChessManager chessManager) {
        super(chessManager);
    }

    public static End of(final ChessManager chessManager, final List<String> inputCommand) {
        validateEndCommand(inputCommand);
        return new End(chessManager);
    }

    private static void validateEndCommand(final List<String> inputCommand) {
        if (inputCommand.size() != END_COMMAND_PARAMETER_COUNT) {
            throw new IllegalArgumentException("유효하지 않은 end 명령어 입니다.");
        }
    }

    @Override
    public void execute() {
        chessManager.gameEnd();
    }

    @Override
    public boolean isEnd() {
        return true;
    }
}
