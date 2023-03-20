package chess.controller;

import chess.controller.Command;
import chess.controller.CommandDto;
import chess.domain.position.Position;

import java.util.Arrays;
import java.util.List;

public class InputRenderer {

    public static CommandDto toCommandDto(final String string) {
        Command command = toCommand(string);
        if (command == Command.MOVE) {
            List<String> commandAndPositions = Arrays.asList(string.split(" "));
            if (commandAndPositions.size() != 3) {
                throw new IllegalArgumentException("source 위치와 target 위치를 입력해야 합니다. 예. move b2 b3");
            }
            Position sourcePosition = toPosition(commandAndPositions.get(1));
            Position targetPosition = toPosition(commandAndPositions.get(2));
            return new CommandDto(command, sourcePosition, targetPosition);
        }
        return new CommandDto(command);
    }

    private static Command toCommand(final String string) {
        Command command;
        try {
            command = Command.valueOf(getUpperCasedFirstWord(string));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("불가능한 명령입니다.");
        }
        return command;
    }

    private static String getUpperCasedFirstWord(String string) {
        return string.split(" ")[0].toUpperCase();
    }

    private static Position toPosition(String rawPosition) {
        if (rawPosition.length() != 2) {
            throw new IllegalArgumentException("유효하지 않은 위치입니다.");
        }
        int column = getColumn(rawPosition);
        int row = getRow(rawPosition);
        return new Position(column, row);
    }

    private static int getColumn(String rawPosition) {
        char file = rawPosition.toCharArray()[0];
        return file - 'a';
    }

    private static int getRow(String rawPosition) {
        char rank = rawPosition.toCharArray()[1];
        return rank - '1';
    }
}
