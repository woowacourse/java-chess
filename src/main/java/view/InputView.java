package view;

import domain.GameCommand;
import dto.RequestDto;

import java.util.Map;
import java.util.Scanner;

public class InputView {
    private static final String START_COMMAND = "start";
    private static final String END_COMMAND = "end";
    private static final Map<String, GameCommand> gameCommands = Map.of(
            START_COMMAND, GameCommand.START,
            END_COMMAND, GameCommand.END
    );

    private final Scanner sc = new Scanner(System.in);

    public RequestDto inputGameCommand() {
        String input = sc.nextLine();
        if (!gameCommands.containsKey(input)) {
            throw new IllegalArgumentException("유효하지 않은 명령입니다.");
        }

        return RequestDto.of(gameCommands.get(input));
    }
}
