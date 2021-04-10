package chess.domain.command;

import chess.domain.manager.ChessManager;

import java.util.List;

public class Start extends Executor {

    private static final int START_COMMAND_PARAMETER_COUNT = 1;

    public Start(ChessManager chessManager) {
        super(chessManager);
    }

    public static Start of(final ChessManager chessManager, final List<String> inputCommand) {
        validateStartCommand(inputCommand);
        return new Start(chessManager);
    }

    private static void validateStartCommand(final List<String> inputCommand) {
        if (inputCommand.size() != START_COMMAND_PARAMETER_COUNT) {
            throw new IllegalArgumentException("유효하지 않은 start 명령어 입니다.");
        }
    }

    @Override
    public void execute() {
    }
}
