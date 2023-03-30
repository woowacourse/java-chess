package chess.controller.dto;

import chess.controller.command.Command;
import chess.controller.command.CommandType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public final class InputRenderer {

    private static final Pattern NUMBER_REGEX = Pattern.compile("^-?[0-9]+$");
    private static final int COMMAND_TYPE_INDEX = 0;
    private static final Map<String, Integer> FILE_TO_COLUMN = new HashMap<>();
    private static final Map<String, Integer> RANK_TO_ROW = new HashMap<>();

    static {
        FILE_TO_COLUMN.put("a", 0);
        FILE_TO_COLUMN.put("b", 1);
        FILE_TO_COLUMN.put("c", 2);
        FILE_TO_COLUMN.put("d", 3);
        FILE_TO_COLUMN.put("e", 4);
        FILE_TO_COLUMN.put("f", 5);
        FILE_TO_COLUMN.put("g", 6);
        FILE_TO_COLUMN.put("h", 7);

        RANK_TO_ROW.put("1", 0);
        RANK_TO_ROW.put("2", 1);
        RANK_TO_ROW.put("3", 2);
        RANK_TO_ROW.put("4", 3);
        RANK_TO_ROW.put("5", 4);
        RANK_TO_ROW.put("6", 5);
        RANK_TO_ROW.put("7", 6);
        RANK_TO_ROW.put("8", 7);
    }

    public static Command toCommand(final List<String> strings) {
        CommandType commandType = toCommandType(strings.get(COMMAND_TYPE_INDEX));
        if (hasOnlyCommandTypeCase(commandType)) {
            validateNoOtherArgument(strings);
            return new Command(commandType);
        }

        if (commandType == CommandType.LOAD) {
            String argument = strings.get(1);
            validateNumber(argument);
            validateNotBigNumber(argument);
            return new Command(commandType, Integer.parseInt(argument));
        }

        validateTwoMoreArguments(strings);
        List<Integer> arguments = new ArrayList<>();
        arguments.addAll(toColumnAndRow(strings.get(1)));
        arguments.addAll(toColumnAndRow(strings.get(2)));
        return new Command(commandType, arguments);
    }

    private static CommandType toCommandType(final String string) {
        try {
            return CommandType.valueOf(string.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("올바르지 않은 명령어입니다.");
        }
    }

    private static void validateNoOtherArgument(List<String> strings) {
        if (strings.size() > 1) {
            throw new IllegalArgumentException("올바르지 않은 명령어입니다.");
        }
    }

    private static void validateNumber(String input) {
        if (!NUMBER_REGEX.matcher(input).matches()) {
            throw new IllegalArgumentException("숫자만 입력할 수 있습니다.");
        }
    }

    private static void validateNotBigNumber(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("서비스 규모가 작아서 큰 단위의 숫자는 지원하지 않습니다.");
        }
    }

    private static void validateTwoMoreArguments(List<String> strings) {
        if (strings.size() != 3) {
            throw new IllegalArgumentException("올바른 명령어를 입력해주세요. 예. move b2 b3");
        }
    }

    private static boolean hasOnlyCommandTypeCase(CommandType commandType) {
        return commandType != CommandType.MOVE && commandType != CommandType.LOAD;
    }

    private static List<Integer> toColumnAndRow(final String rawPosition) {
        if (rawPosition.length() != 2) {
            throw new IllegalArgumentException("올바르지 않은 명령어입니다.");
        }

        String file = String.valueOf(rawPosition.charAt(0));
        String rank = String.valueOf(rawPosition.charAt(1));
        if (!FILE_TO_COLUMN.containsKey(file)) {
            throw new IllegalArgumentException("유효하지 않은 File 입니다.");
        }
        if (!RANK_TO_ROW.containsKey(rank)) {
            throw new IllegalArgumentException("유효하지 않은 Rank 입니다.");
        }
        return List.of(FILE_TO_COLUMN.get(file), RANK_TO_ROW.get(rank));
    }
}
