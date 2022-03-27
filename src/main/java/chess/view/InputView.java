package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String START = "start";
    private static final String END = "end";
    private static final String MOVE = "move";
    private static final String STATUS = "status";
    private static final String SPLIT_REGEX = " ";
    private static final String ERROR_MESSAGE_COMMAND = "[ERROR] 그런 명령어는 없는뎅...ㅎ;;";
    private static final String ERROR_MESSAGE_LACK_INFORMATION = "[ERROR] 부족해잉~ 더줘잉~";
    private static final String ERROR_MESSAGE_POSITION_FORMAT = "[ERROR] 위치의 포맷을 지켜서 입력하세요.";
    private static final String ERROR_MESSAGE_IMPOSSIBLE_COMMAND = "[ERROR] 지금은 앙댕! 혼난다??";
    private static final String ERROR_MESSAGE_TMI = "[ERROR] 투 머치 인포메이션~ㅋ";
    private static final String MESSAGE_GAME_END = "king 잡았다!";

    private static final int COMMAND_INDEX = 0;
    private static final int COMMAND_MOVE_SOURCE_INDEX = 1;
    private static final int COMMAND_MOVE_TARGET_INDEX = 2;
    private static final int COMMAND_MOVE_FORMAT_SIZE = 3;
    private static final int POSITION_SIZE = 2;
    private static final int COMMAND_NOT_MOVE_FORMAT_SIZE = 1;

    public static boolean isStart() {
        try {
            String command = getCommands().get(COMMAND_INDEX);
            checkImpossibleCommand(MOVE, command);
            checkImpossibleCommand(STATUS,command);
            return START.equals(command);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return isStart();
        }
    }

    public static List<String> requireCommand() {
        try {
            List<String> commands = getCommands();
            String command = commands.get(COMMAND_INDEX);
            checkImpossibleCommand(START, command);
            checkImpossibleCommand(STATUS,command);

            commands.remove(COMMAND_INDEX);
            return commands;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return requireCommand();
        }
    }

    public static boolean isGameEnd() {
        System.out.println(MESSAGE_GAME_END);
        try {
            String command = getCommands().get(COMMAND_INDEX);
            checkImpossibleCommand(START, command);
            checkImpossibleCommand(MOVE, command);
            return END.equals(command);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return isGameEnd();
        }
    }

    private static void checkImpossibleCommand(String command, String input) {
        if (command.equals(input)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_IMPOSSIBLE_COMMAND);
        }
    }

    private static List<String> getCommands() {
        List<String> commands = splitCommands();
        validateCommands(commands);
        return commands;
    }

    private static List<String> splitCommands() {
        String answer = scanner.nextLine().trim().toLowerCase(Locale.ROOT);
        return Arrays.stream(answer.split(SPLIT_REGEX))
                .filter(cmd -> !cmd.isBlank())
                .collect(Collectors.toList());
    }

    private static void validateCommands(List<String> commands) {
        String command = commands.get(COMMAND_INDEX);

        if (!START.equals(command) && !END.equals(command)
                && !MOVE.equals(command)&&!STATUS.equals(command)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_COMMAND);
        }

        validateCommand(commands, command);
    }

    private static void validateCommand(List<String> commands, String command) {
        if (START.equals(command) || END.equals(command)
                || STATUS.equals(command)) {
            validateInformationCount(commands, COMMAND_NOT_MOVE_FORMAT_SIZE);
        }

        if (MOVE.equals(command)) {
            validateInformationCount(commands,COMMAND_MOVE_FORMAT_SIZE);
            validatePositionFormat(commands);
        }
    }

    private static void validateInformationCount(List<String> commands, int size) {
        if (commands.size() < size) {
            throw new IllegalArgumentException(ERROR_MESSAGE_LACK_INFORMATION);
        }

        if (commands.size() > size) {
            throw new IllegalArgumentException(ERROR_MESSAGE_TMI);
        }
    }

    private static void validatePositionFormat(List<String> commands) {
        String source = commands.get(COMMAND_MOVE_SOURCE_INDEX);
        String target = commands.get(COMMAND_MOVE_TARGET_INDEX);

        if (source.length() != POSITION_SIZE || target.length() != POSITION_SIZE) {
            throw new IllegalArgumentException(ERROR_MESSAGE_POSITION_FORMAT);
        }
    }
}
