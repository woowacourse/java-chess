package chess.domain;

import chess.view.Output;

import java.util.Arrays;
import java.util.function.BiConsumer;

public enum Command {

    START("start", (input, chessGame) -> chessGame.start()),
    END("end", (input, chessGame) -> chessGame.end()),
    MOVE("move", (input, chessGame) -> {
        checkMoveCommandLength(input);
        chessGame.move(input);
    }),
    STATUS("status", (input, chessGame) -> chessGame.status(Output::printScore)),
    ;

    private static final int COMMAND_LOCATION = 0;
    private static final int MOVE_COMMAND_LENGTH = 3;

    private final String command;
    private final BiConsumer<String[], ChessGame> operate;

    Command(final String command, final BiConsumer<String[], ChessGame> operate) {
        this.command = command;
        this.operate = operate;
    }

    public static void execute(final String input, final ChessGame chessGame) {
        String[] commands = input.split(" ");
        Arrays.stream(Command.values())
                .filter(command -> commands[COMMAND_LOCATION].equals(command.command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 명령어 입니다."))
                .operate
                .accept(commands, chessGame);
    }

    private static void checkMoveCommandLength(final String[] commands) {
        if (commands.length != MOVE_COMMAND_LENGTH) {
            throw new IllegalArgumentException("'move source위치 target위치' 의 형식으로 입력해주세요.");
        }
    }
}
