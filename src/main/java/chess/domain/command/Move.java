package chess.domain.command;

import chess.domain.board.position.Position;
import chess.domain.manager.ChessManager;

import java.util.List;

public class Move extends Executor {

    private static final int MENU_COMMAND_PARAMETER_COUNT = 3;
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;

    private final Position source;
    private final Position target;

    private Move(final ChessManager chessManager, final Position source, final Position target) {
        super(chessManager);
        this.source = source;
        this.target = target;
    }

    public static Move of(final ChessManager chessManager, final String source, final String target) {
        return new Move(chessManager, Position.of(source), Position.of(target));
    }

    public static Move of(final ChessManager chessManager, final List<String> inputCommand) {
        validateMoveCommand(inputCommand);
        return Move.of(chessManager, inputCommand.get(SOURCE_INDEX), inputCommand.get(TARGET_INDEX));
    }

    private static void validateMoveCommand(final List<String> inputCommand) {
        if (inputCommand.size() != MENU_COMMAND_PARAMETER_COUNT) {
            throw new IllegalArgumentException("유효하지 않은 move 명령어 입니다.");
        }
    }

    @Override
    public void execute() {
        chessManager.move(source, target);
    }

    @Override
    public boolean isMove() {
        return true;
    }
}
