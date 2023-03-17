package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String START_MESSAGE = "체스 게임을 시작합니다.";
    private static final String COMMAND_MESSAGE = "게임 시작은 start, 종료는 end 명령을 입력하세요.";
    private static final String DELIMITER = " ";

    private static final Scanner scanner = new Scanner(System.in);

    public void printStartMessage() {
        System.out.println(START_MESSAGE);
        System.out.println(COMMAND_MESSAGE);
    }

    public List<String> requestCommand() {
        String input = scanner.nextLine();

        if (input.isEmpty()) {
            throw new IllegalArgumentException("올바르지 않은 명령어 입니다.");
        }

        List<String> commands = List.of(input.split(DELIMITER, -1));
        validateCommand(commands);

        return commands;
    }

    private void validateCommand(List<String> commands) {
        Command command = Command.renderToCommand(commands.get(CommandRule.MAIN_COMMAND_INDEX.value));

        if (command == Command.MOVE) {
            validateMoveCommand(commands);
            return;
        }

        validateStartAndEndCommand(commands);
    }

    private void validateMoveCommand(List<String> commands) {
        validateMoveCommandSize(commands);
        validateSquareCommandSize(commands);
    }

    private void validateMoveCommandSize(List<String> commands) {
        if (commands.size() != CommandRule.MOVE_COMMAND_SIZE.value) {
            throw new IllegalArgumentException("move 명령어는 [move source target] 입니다.");
        }
    }

    private void validateSquareCommandSize(List<String> commands) {
        String source = commands.get(CommandRule.SOURCE_COMMAND_INDEX.value);
        String target = commands.get(CommandRule.TARGET_COMMAND_INDEX.value);

        if (source.length() != CommandRule.SQUARE_COMMAND_SIZE.value
                || target.length() != CommandRule.SQUARE_COMMAND_SIZE.value) {
            throw new IllegalArgumentException("잘못된 위치에 대한 입력입니다.");
        }
    }

    private void validateStartAndEndCommand(List<String> commands) {
        if (commands.size() != CommandRule.NORMAL_COMMAND_SIZE.value) {
            throw new IllegalArgumentException("존재하지 않는 명령어 입니다.");
        }
    }

    private enum Command {
        START("start"),
        END("end"),
        MOVE("move");

        private final String command;

        Command(String command) {
            this.command = command;
        }

        private static Command renderToCommand(String input) {
            return Arrays.stream(values())
                    .filter(value -> value.command.equals(input))
                    .findAny()
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 명령어입니다."));
        }
    }

    private enum CommandRule {
        MAIN_COMMAND_INDEX(0),
        SOURCE_COMMAND_INDEX(1),
        TARGET_COMMAND_INDEX(2),
        NORMAL_COMMAND_SIZE(1),
        SQUARE_COMMAND_SIZE(2),
        MOVE_COMMAND_SIZE(3),
        ;

        private final int value;

        CommandRule(int value) {
            this.value = value;
        }
    }
}
