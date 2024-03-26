package domain.command;

import domain.game.Executable;
import domain.position.Position;
import java.util.List;
import java.util.regex.Pattern;

public class MoveCommand implements Command {
    private static final String MOVE_COMMAND_REGEX_FORMAT = "^[a-h][1-8]";
    private static final Pattern MOVE_COMMAND_PATTERN = Pattern.compile(MOVE_COMMAND_REGEX_FORMAT);
    public static final int SOURCE_INDEX = 0;
    public static final int TARGET_INDEX = 1;
    public static final int MOVE_ARGUMENTS_SIZE = 2;

    private final Position source;
    private final Position target;

    public MoveCommand(List<String> arguments) {
        validate(arguments);
        this.source = new Position(arguments.get(SOURCE_INDEX));
        this.target = new Position(arguments.get(TARGET_INDEX));
    }


    private void validate(List<String> arguments) {
        checkArgumentCount(arguments);
        checkArgumentPattern(arguments);
    }

    private void checkArgumentCount(List<String> arguments) {
        if (arguments.size() != MOVE_ARGUMENTS_SIZE) {
            throw new IllegalArgumentException("잘못된 move 명령어 입니다.");
        }
    }

    private void checkArgumentPattern(List<String> arguments) {
        if (isNotAllMatchToPattern(arguments)) {
            throw new IllegalArgumentException("source와 target을 다시 입력해주세요");
        }
    }


    private boolean isNotAllMatchToPattern(List<String> arguments) {
        return !arguments.stream()
                .allMatch(this::isValidPositionPattern);
    }

    private boolean isValidPositionPattern(final String input) {
        return MOVE_COMMAND_PATTERN.matcher(input).matches();
    }


    @Override
    public void execute(Executable executable) {
        executable.move(source, target);
    }
}
