package chess.domain.command;

import chess.domain.manager.ChessManager;

import java.util.List;

public class Restart extends Executor {

    private static final int RESTART_COMMAND_PARAMETER_COUNT = 1;

    public Restart(ChessManager chessManager) {
        super(chessManager);
    }

    public static Restart of(final ChessManager chessManager, final List<String> inputCommand) {
        validateStartCommand(inputCommand);
        return new Restart(chessManager);
    }

    private static void validateStartCommand(final List<String> inputCommand) {
        if (inputCommand.size() != RESTART_COMMAND_PARAMETER_COUNT) {
            throw new IllegalArgumentException("유효하지 않은 restart 명령어 입니다.");
        }
    }

    @Override
    public void execute() {
        chessManager.resetBoard();
    }

    @Override
    public boolean isRestart() {
        return true;
    }
}
