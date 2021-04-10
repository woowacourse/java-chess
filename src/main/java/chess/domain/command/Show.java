package chess.domain.command;

import chess.domain.board.position.Path;
import chess.domain.board.position.Position;
import chess.domain.manager.ChessManager;

import java.util.List;

public class Show extends Executor {

    private static final int SHOW_COMMAND_PARAMETER_COUNT = 2;
    private static final int SOURCE_INDEX = 1;

    private final Position source;
    private Path path;

    private Show(final ChessManager chessManager, final Position source) {
        super(chessManager);
        this.source = source;
    }

    public static Show of(final ChessManager chessManager, final List<String> inputCommand) {
        validateShowCommand(inputCommand);
        return new Show(chessManager, Position.of(inputCommand.get(SOURCE_INDEX)));
    }

    private static void validateShowCommand(final List<String> inputCommand) {
        if (inputCommand.size() != SHOW_COMMAND_PARAMETER_COUNT) {
            throw new IllegalArgumentException("유효하지 않은 show 명령어 입니다.");
        }
    }

    public Path path() {
        return this.path;
    }

    @Override
    public void execute() {
        this.path = chessManager.movablePath(source);
    }

    @Override
    public boolean isShow() {
        return true;
    }
}
