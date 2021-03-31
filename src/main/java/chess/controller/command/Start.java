package chess.controller.command;

import chess.controller.ChessController;

import java.util.List;

public class Start extends Executer {

    private static final int START_COMMAND_PARAMETER_COUNT = 1;

    public Start(final ChessController chessController) {
        super(chessController);
    }

    public static Start of(final ChessController chessController, final List<String> inputCommand) {
        validateStartCommand(inputCommand);
        return new Start(chessController);
    }

    private static void validateStartCommand(final List<String> inputCommand) {
        if (inputCommand.size() != START_COMMAND_PARAMETER_COUNT) {
            throw new IllegalArgumentException("유효하지 않은 start 명령어 입니다.");
        }
    }

    @Override
    public boolean isStart() {
        return true;
    }

    @Override
    public void execute() {
        chessController.startGame();
    }
}
