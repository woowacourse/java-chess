package chess.dto.request;

import java.util.Arrays;
import java.util.List;

public class ConsoleCommandDto {

    private final Command command;
    private final List<String> arguments;

    private ConsoleCommandDto(Command command, List<String> arguments) {
        this.command = command;
        this.arguments = arguments;
    }

    public static ConsoleCommandDto from(String command, List<String> arguments) {
        return new ConsoleCommandDto(Command.from(command), arguments);
    }

    public boolean isStart() {
        return command == Command.START;
    }

    public boolean isMove() {
        return command == Command.MOVE;
    }

    public boolean isStatus() {
        return command == Command.STATUS;
    }

    public boolean isEnd() {
        return command == Command.END;
    }

    public String getArgumentByIndex(int index) {
        return arguments.get(index);
    }

    @Override
    public String toString() {
        return "ConsoleCommandDto{" +
                "command=" + command +
                ", arguments=" + arguments +
                '}';
    }

    private enum Command {
        START("start"),
        MOVE("move"),
        STATUS("status"),
        END("end");

        private final String consoleInput;

        Command(String consoleInput) {
            this.consoleInput = consoleInput;
        }

        public static Command from(String consoleInput) {
            return Arrays.stream(values())
                    .filter(command -> consoleInput.equals(command.consoleInput))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 명령입니다."));
        }
    }
}
