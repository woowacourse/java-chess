package chess.view;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import chess.domain.command.CommandType;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String SPLIT_REGEX = " ";
    private static final String ERROR_MESSAGE_LACK_INFORMATION = "[ERROR] 부족해잉~ 더줘잉~";
    private static final String ERROR_MESSAGE_POSITION_FORMAT = "[ERROR] 위치의 포맷을 지켜서 입력하세요.";
    private static final String ERROR_MESSAGE_TMI = "[ERROR] 투 머치 인포메이션~ㅋ";

    private static final int COMMAND_INDEX = 0;
    private static final int COMMAND_MOVE_SOURCE_INDEX = 1;
    private static final int COMMAND_MOVE_TARGET_INDEX = 2;
    private static final int COMMAND_MOVE_FORMAT_SIZE = 3;
    private static final int POSITION_SIZE = 2;
    private static final int COMMAND_NOT_MOVE_FORMAT_SIZE = 1;

    public static List<String> getCommand() {
        List<String> commands = getCommands();
        try {
            return convertInputToCommand(commands);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getCommand();
        }
    }

    private static List<String> getCommands() {
        String answer = scanner.nextLine().trim().toLowerCase(Locale.ROOT);
        return List.of(answer.split(SPLIT_REGEX));
    }

    private static List<String> convertInputToCommand(List<String> commands) {
        CommandType command = CommandType.find(commands.get(COMMAND_INDEX));
        if (commands.size() == COMMAND_NOT_MOVE_FORMAT_SIZE && command != CommandType.MOVE) {
            return commands;
        }
        validateMoveCommandSize(commands);
        return getMoveCommand(commands);
    }

    private static void validateMoveCommandSize(List<String> commands) {
        if (commands.size() > COMMAND_MOVE_FORMAT_SIZE) {
            throw new IllegalArgumentException(ERROR_MESSAGE_TMI);
        }
        if (commands.size() < COMMAND_MOVE_FORMAT_SIZE) {
            throw new IllegalArgumentException(ERROR_MESSAGE_LACK_INFORMATION);
        }
    }

    private static List<String> getMoveCommand(List<String> commands) {
        String source = commands.get(COMMAND_MOVE_SOURCE_INDEX);
        String target = commands.get(COMMAND_MOVE_TARGET_INDEX);
        validatePositionFormat(source, target);
        return commands;
    }

    private static void validatePositionFormat(String source, String target) {
        if (source.length() != POSITION_SIZE && target.length() != POSITION_SIZE) {
            throw new IllegalArgumentException(ERROR_MESSAGE_POSITION_FORMAT);
        }
    }
}
