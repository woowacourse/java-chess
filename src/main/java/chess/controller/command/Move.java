package chess.controller.command;

import chess.controller.ChessController;
import chess.domain.board.position.Position;

import java.util.List;

public class Move extends Executer {

    private static final int MENU_COMMAND_PARAMETER_COUNT = 3;
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;

    private final Position source;
    private final Position target;

    private Move(ChessController chessController, final Position source, final Position target) {
        super(chessController);
        this.source = source;
        this.target = target;
    }

    public static Move of(final ChessController chessController, final String source, final String target) {
        return new Move(chessController, Position.of(source), Position.of(target));
    }

    public static Move of(final ChessController chessController, final List<String> inputCommand) {
        validateMoveCommand(inputCommand);
        return Move.of(chessController, inputCommand.get(SOURCE_INDEX), inputCommand.get(TARGET_INDEX));
    }

    private static void validateMoveCommand(List<String> inputCommand) {
        if (inputCommand.size() != MENU_COMMAND_PARAMETER_COUNT) {
            throw new IllegalArgumentException("유효하지 않은 move 명령어 입니다.");
        }
    }

    @Override
    public void execute() {
        chessController.move(source, target);
    }
}
