package chess.domain.command;

import chess.domain.manager.ChessManager;
import chess.domain.manager.GameStatus;

import java.util.List;

public class Status extends Executor {

    private static final int STATUS_COMMAND_PARAMETER_COUNT = 1;

    private GameStatus gameStatus;

    public Status(ChessManager chessManager) {
        super(chessManager);
    }

    public static Status of(final ChessManager chessManager, final List<String> inputCommand) {
        validateStatusCommand(inputCommand);
        return new Status(chessManager);
    }

    private static void validateStatusCommand(final List<String> inputCommand) {
        if (inputCommand.size() != STATUS_COMMAND_PARAMETER_COUNT) {
            throw new IllegalArgumentException("유효하지 않은 status 명령어 입니다.");
        }
    }

    public GameStatus gameStatus() {
        return this.gameStatus;
    }

    @Override
    public void execute() {
        this.gameStatus = chessManager.gameStatus();
    }

    @Override
    public boolean isStatus() {
        return true;
    }
}
