package chess.controller.dto;

import chess.controller.Command;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputRenderer {

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

    public static CommandDto toCommandDto(final String string) {
        Command command = toCommand(string);
        if (command != Command.MOVE) {
            return new CommandDto(command);
        }

        List<String> commandAndPositions = Arrays.asList(string.split(" "));
        if (commandAndPositions.size() != 3) {
            throw new IllegalArgumentException("올바른 명령어를 입력해주세요. 예. move b2 b3");
        }
        List<Integer> source = toColumnAndRow(commandAndPositions.get(1));
        List<Integer> target = toColumnAndRow(commandAndPositions.get(2));
        return new CommandDto(command, source, target);
    }

    private static Command toCommand(final String string) {
        try {
            return Command.valueOf(getUpperCasedFirstWord(string));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("올바르지 않은 명령어입니다.");
        }
    }

    private static String getUpperCasedFirstWord(final String string) {
        return string.split(" ")[0].toUpperCase();
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
