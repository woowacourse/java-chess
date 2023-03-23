package chess.view;

import java.util.Arrays;
import java.util.List;

public enum ChessCommand {
    START("start"),
    MOVE("move"),
    END("end"),
    WAIT("Wait"),
    STATUS("status"),
    ;

    private static final int COMMAND_INDEX = 0;
    public static final int MOVE_INPUT_SIZE = 3;
    public static final int STATUS_INPUT_SIZE = 1;
    public static final int START_INPUT_SIZE = 1;
    private final String command;

    ChessCommand(String command) {
        this.command = command;
    }

    public static ChessCommand from(String inputCommand) {
        return Arrays.stream(ChessCommand.values())
                .filter(it -> it.command.equals(inputCommand))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 명령어는 존재하지 않습니다."));
    }

    public static void validatePlayingCommand(List<String> inputCommand) {
        ChessCommand chessCommand = from(inputCommand.get(COMMAND_INDEX));
        if (chessCommand == MOVE && inputCommand.size() != MOVE_INPUT_SIZE) {
            throw new IllegalArgumentException("move를 하기 위해서는 형식의 맞는 입력 해야합니다. 예) move b2 b3");
        }
    }

    public static void validateStartCommand(List<String> inputCommand) {
        ChessCommand chessCommand = from(inputCommand.get(COMMAND_INDEX));
        if (chessCommand != START && inputCommand.size() != START_INPUT_SIZE) {
            throw new IllegalArgumentException("start를 하기 위해서는 형식의 맞는 입력 해야합니다. 예) start");
        }
    }

    public static void validateStatusCommand(final List<String> inputCommand) {
        ChessCommand chessCommand = from(inputCommand.get(COMMAND_INDEX));
        if (chessCommand != STATUS && inputCommand.size() != STATUS_INPUT_SIZE) {
            throw new IllegalArgumentException("status를 하기 위해서는 형식의 맞는 입력 해야합니다. 예) status");
        }
    }
}
