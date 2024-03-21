package view;

import domain.GameCommand;
import dto.RequestDto;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static view.PositionConvertor.convertPosition;

public class InputView {
    private static final String START_COMMAND = "start";
    private static final String MOVE_COMMAND = "move";
    private static final String END_COMMAND = "end";
    private static final Map<String, GameCommand> gameCommands = Map.of(
            START_COMMAND, GameCommand.START,
            MOVE_COMMAND, GameCommand.MOVE,
            END_COMMAND, GameCommand.END
    );

    private final Scanner sc = new Scanner(System.in);

    public GameCommand inputGameStart() {
        String input = sc.nextLine();
        if (!gameCommands.containsKey(input)) {
            throw new IllegalArgumentException("잘못된 명령입니다.");
        }
        return gameCommands.get(input);
    }

    public RequestDto inputGameCommand() {
        List<String> input = Arrays.stream(sc.nextLine().split(" ")).toList();
        if (!gameCommands.containsKey(input.get(0))) {
            throw new IllegalArgumentException("유효하지 않은 명령입니다.");
        }

        if (input.size() == 3) {
            return RequestDto.of(gameCommands.get(input.get(0)), convertPosition(input.get(1)), convertPosition(input.get(2)));
        }

        return RequestDto.of(gameCommands.get(input.get(0)));
    }
}
