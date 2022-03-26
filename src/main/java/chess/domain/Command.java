package chess.domain;

import java.util.Arrays;
import java.util.function.BiConsumer;

public enum Command {

    START("start", (input, chessGame) -> chessGame.start()),
    END("end", (input, chessGame) -> chessGame.end()),
    MOVE("move", (input, chessGame) -> chessGame.move(input)),
    ;

    private static final int COMMAND_LOCATION = 0;

    private final String command;
    private final BiConsumer<String[], ChessGame> operate;

    Command(final String command, final BiConsumer<String[], ChessGame> operate) {
        this.command = command;
        this.operate = operate;
    }

    public static void execute(final String[] input, final ChessGame chessGame) {
        Arrays.stream(Command.values())
                .filter(command -> input[COMMAND_LOCATION].equals(command.command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 명령어 입니다."))
                .operate
                .accept(input, chessGame);
    }

    private void checkMoveCommandLength(final String[] command) {
        if (command.length != 3) {
            throw new IllegalArgumentException("'move source위치 target위치' 의 형식으로 입력해주세요.");
        }
    }
}
