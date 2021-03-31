package chess.controller.command;

import chess.controller.ChessController;
import chess.domain.board.position.Position;

import java.util.List;

public class Show extends Executer {

    private static final int SHOW_COMMAND_PARAMETER_COUNT = 2;
    private static final int SOURCE_INDEX = 1;

    private final Position source;

    private Show(final ChessController chessController, final Position source) {
        super(chessController);
        this.source = source;
    }

    public static Show of(final ChessController chessController, final List<String> inputCommand) {
        validateShowCommand(inputCommand);
        return new Show(chessController, Position.of(inputCommand.get(SOURCE_INDEX)));
    }

    private static void validateShowCommand(final List<String> inputCommand) {
        if (inputCommand.size() != SHOW_COMMAND_PARAMETER_COUNT) {
            throw new IllegalArgumentException("유효하지 않은 show 명령어 입니다.");
        }
    }

    @Override
    public void execute() {
        chessController.show(source);
    }
}
