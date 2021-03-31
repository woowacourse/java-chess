package chess.controller.command;

import chess.controller.ChessController;

import java.util.List;

public class Restart extends Executer {

    private static final int RESTART_COMMAND_PARAMETER_COUNT = 1;

    public Restart(final ChessController chessController) {
        super(chessController);
    }

    public static Restart of(final ChessController chessController, final List<String> inputCommand) {
        validateStartCommand(inputCommand);
        return new Restart(chessController);
    }

    private static void validateStartCommand(final List<String> inputCommand) {
        if (inputCommand.size() != RESTART_COMMAND_PARAMETER_COUNT) {
            throw new IllegalArgumentException("유효하지 않은 restart 명령어 입니다.");
        }
    }

    @Override
    public void execute() {
        chessController.restart();
    }
}
