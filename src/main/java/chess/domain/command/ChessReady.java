package chess.domain.command;

public class ChessReady {

    private static final String ILLEGAL_ARGUMENT_ERROR_MESSAGE = "명령어가 잘못되었습니다";

    private ChessReady() {
    }

    public static CommandState startCommand(String input) {
        Command command = Command.find(input);

        if (command == Command.START) {
            return new Start();
        }
        if (command == Command.END) {
            return new End();
        }
        throw new IllegalArgumentException(ILLEGAL_ARGUMENT_ERROR_MESSAGE);
    }
}
