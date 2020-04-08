package chess.view;

import chess.command.Command;
import chess.controller.dto.RequestDto;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final int COMMAND_INDEX = 0;
    private static final int PARAMETER_START_INDEX = 1;
    private static final String COMMAND_DELIMITER = " ";

    private static final Scanner scanner = new Scanner(System.in);

    public static RequestDto inputRequest() {
        System.out.println("명령어를 입력하세요>");
        String input = scanner.nextLine();
        List<String> commands = Arrays.asList(input.split(COMMAND_DELIMITER));
        Command command = Command.of(commands.get(COMMAND_INDEX));
        if (hasParams(commands)) {
            return new RequestDto(command, commands.subList(PARAMETER_START_INDEX, commands.size()));
        }
        return new RequestDto(command);
    }

    private static boolean hasParams(List<String> commands) {
        return commands.size() > PARAMETER_START_INDEX;
    }

}
