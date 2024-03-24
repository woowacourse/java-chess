package chess.view;

import chess.domain.Command;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    public static final int MOVE_COMMAND_PART_COUNT = 3;
    public static final int MOVE_COMMAND_POSITION_START_INDEX = 1;
    public static final int MOVE_COMMAND_MESSAGE_INDEX = 0;
    private final Scanner scanner = new Scanner(System.in);

    public String readStartCommand() {
        System.out.println("> 체스 게임을 시작합니다." + System.lineSeparator()
                + "> 게임 시작 : " + Command.START + System.lineSeparator()
                + "> 게임 종료 : " + Command.END + System.lineSeparator()
                + "> 게임 이동 :  " + Command.MOVE + "source위치 target위치 - 예. " + Command.MOVE + " b2 b3");
        return scanner.nextLine();
    }


    public List<String> readMoveCommand() {
        final String input = scanner.nextLine();
        validateBlank(input);

        if (Command.END.getMessage().equals(input)) {
            return List.of();
        }

        List<String> values = Arrays.asList(input.split(" "));
        validateMoveCommand(values);
        return values.subList(MOVE_COMMAND_POSITION_START_INDEX, MOVE_COMMAND_PART_COUNT);
    }

    private void validateMoveCommand(final List<String> splittedInput) {
        validateMoveCommandPartCount(splittedInput);
        validateMoveCommandMessage(splittedInput);
        validateRankNumeric(splittedInput);
    }

    private void validateRankNumeric(final List<String> splittedInput) {
        splittedInput.subList(MOVE_COMMAND_POSITION_START_INDEX, MOVE_COMMAND_PART_COUNT)
                .forEach(position -> validateNumeric(position.substring(MOVE_COMMAND_POSITION_START_INDEX)));
    }

    private void validateMoveCommandMessage(final List<String> splittedInput) {
        if (!splittedInput.get(MOVE_COMMAND_MESSAGE_INDEX).equals(Command.MOVE.getMessage())) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력입니다.");
        }
    }

    private void validateMoveCommandPartCount(final List<String> splittedInput) {
        if (splittedInput.size() != MOVE_COMMAND_PART_COUNT) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력입니다.");
        }
    }

    private void validateNumeric(final String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력입니다.");
        }
    }

    private void validateBlank(final String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력입니다.");
        }
    }
}
