package view;

import domain.game.GameCommand;
import dto.MoveRequestDto;
import dto.PositionDto;

import java.util.Map;
import java.util.Scanner;

public class InputView {
    private static final String START_COMMAND = "start";
    private static final String MOVE_COMMAND = "move";
    private static final String END_COMMAND = "end";
    private static final Map<String, GameCommand> gameCommands = Map.of(
            START_COMMAND, GameCommand.START,
            MOVE_COMMAND, GameCommand.MOVE,
            END_COMMAND, GameCommand.END
    );
    private static final Map<String, Integer> fileIndexes = Map.ofEntries(
            Map.entry("a", 0),
            Map.entry("b", 1),
            Map.entry("c", 2),
            Map.entry("d", 3),
            Map.entry("e", 4),
            Map.entry("f", 5),
            Map.entry("g", 6),
            Map.entry("h", 7),
            Map.entry("8", 0),
            Map.entry("7", 1),
            Map.entry("6", 2),
            Map.entry("5", 3),
            Map.entry("4", 4),
            Map.entry("3", 5),
            Map.entry("2", 6),
            Map.entry("1", 7)
    );

    private final Scanner sc = new Scanner(System.in);

    public GameCommand inputGameStartCommand() {
        String input = sc.nextLine();
        validateInputStringEmpty(input);
        validateInputCommand(input);

        return gameCommands.get(input);
    }

    private void validateInputStringEmpty(final String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("공백을 입력할 수 없습니다.");
        }

        if (input.equals(MOVE_COMMAND)) {
            throw new IllegalArgumentException("START 혹은 END 명령만 입력할 수 있습니다.");
        }
    }

    private void validateInputCommand(final String input) {
        if (!gameCommands.containsKey(input)) {
            throw new IllegalArgumentException("존재하지 않는 명령입니다.");
        }
    }

    public MoveRequestDto inputMoveRequest() {
        String input = sc.nextLine();
        validateInputStringEmpty(input);

        String[] inputStrings = input.split(" ");
        validateInputCommand(inputStrings);

        GameCommand gameCommand = gameCommands.get(inputStrings[0]);
        if (!gameCommand.isMoveCommand()) {
            return MoveRequestDto.of(gameCommand);
        }

        PositionDto source = convertInputToPosition(inputStrings[1]);
        PositionDto destination = convertInputToPosition(inputStrings[2]);

        return new MoveRequestDto(gameCommand, source, destination);
    }

    private void validateInputCommand(final String[] input) {
        if (input.length != 1 && input.length != 3) {
            throw new IllegalArgumentException("잘못된 입력입니다. 올바른 형식으로 입력해주세요. ex) move b2 b3");
        }

        if (!gameCommands.containsKey(input[0])) {
            throw new IllegalArgumentException("존재하지 않는 명령입니다.");
        }

        if (input[0].equals(START_COMMAND)) {
            throw new IllegalArgumentException("MOVE 혹은 END 명령만 입력할 수 있습니다.");
        }
    }

    private PositionDto convertInputToPosition(final String input) {
        if (input.length() != 2) {
            throw new IllegalArgumentException("잘못된 입력입니다. 올바른 형식으로 입력해주세요. ex) move b2 b3");
        }

        String[] inputStrings = input.split("");
        int rowIndex = parseIndex(inputStrings[0]);
        int columnIndex = parseIndex(inputStrings[1]);

        return new PositionDto(rowIndex, columnIndex);
    }

    private int parseIndex(final String input) {
        if (!fileIndexes.containsKey(input.toLowerCase())) {
            throw new IllegalArgumentException("잘못된 입력입니다. 올바른 형식으로 입력해주세요. ex) move b2 b3");
        }

        return fileIndexes.get(input.toLowerCase());
    }
}
