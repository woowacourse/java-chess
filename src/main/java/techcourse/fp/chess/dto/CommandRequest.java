package techcourse.fp.chess.dto;

import java.util.List;
import techcourse.fp.chess.domain.File;
import techcourse.fp.chess.domain.Position;
import techcourse.fp.chess.domain.Rank;

public class CommandRequest {

    private static final int FILE_ASCII_OFFSET = 96;
    private static final char RANK_ASCII_OFFSET = '0';
    private static final int SOURCE_IDX = 1;
    private static final int TARGET_IDX = 2;
    private static final int MOVE_COMMAND_SIZE = 3;
    private static final int OTHER_COMMAND_SIZE = 1;

    private final String message;
    private final Position source;
    private final Position target;

    public CommandRequest(final String message, final Position source, final Position target) {
        this.message = message;
        this.source = source;
        this.target = target;
    }

    public static CommandRequest create(List<String> commands) {

        if (commands.size() == OTHER_COMMAND_SIZE && !isMoveCommand(getRawMessage(commands))) {
            return new CommandRequest(getRawMessage(commands), getEmptyPosition(), getEmptyPosition());
        }

        if (commands.size() == MOVE_COMMAND_SIZE && isMoveCommand(getRawMessage(commands))) {
            return new CommandRequest(getRawMessage(commands),
                    parseToPosition(commands.get(SOURCE_IDX)),
                    parseToPosition(commands.get(TARGET_IDX)));
        }

        throw new IllegalArgumentException("올바른 커맨드 값을 입력해주세요.");
    }

    private static String getRawMessage(final List<String> commands) {
        return commands.get(0);
    }

    private static boolean isMoveCommand(final String command) {
        return command.equalsIgnoreCase("move");
    }

    private static Position getEmptyPosition() {
        return Position.of(null, null);
    }

    private static Position parseToPosition(final String command) {
        if (command.length() != 2) {
            throw new IllegalStateException("정확한 좌표를 입력해주세요.");
        }

        final int fileOrder = command.charAt(0) - FILE_ASCII_OFFSET;
        final int rankOrder = command.charAt(1) - RANK_ASCII_OFFSET;

        return Position.of(File.of(fileOrder), Rank.of(rankOrder));
    }

    public String getMessage() {
        return message;
    }

    public Position getSource() {
        return source;
    }

    public Position getTarget() {
        return target;
    }
}
