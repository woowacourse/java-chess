package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String GAME_COMMAND_DELIMITER = " ";
    private static final int GAME_COMMAND_INDEX = 0;
    private static final String START_COMMAND = "start";
    private static final String END_COMMAND = "end";
    private static final String MOVE_COMMAND = "move";
    private static final String STATUS_COMMAND = "status";
    private static final int CORRECT_START_END_SPLIT_SIZE = 1;
    private static final int CORRECT_MOVE_SPLIT_SIZE = 3;
    private static final int SOURCE_POSITION_INDEX = 1;
    private static final int TARGET_POSITION_INDEX = 2;
    private static final int RANK_INDEX = 1;
    private static final String NUMERIC_REGEX = "[0-9]";

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public List<String> inputGameCommand() {
        String input = scanner.nextLine();
        final List<String> splitGameCommand = Arrays.asList(input.split(GAME_COMMAND_DELIMITER));
        final String gameCommand = splitGameCommand.get(GAME_COMMAND_INDEX);

        validateGameCommandFormat(splitGameCommand);
        validateMoveGameCommandRank(splitGameCommand, gameCommand);

        return splitGameCommand;
    }

    private void validateGameCommandFormat(final List<String> splitGameCommand) {
        final String gameCommand = splitGameCommand.get(GAME_COMMAND_INDEX);
        final int splitGameCommandSize = splitGameCommand.size();
        if (isWrongStartOrEndCommandFormat(splitGameCommandSize, gameCommand)) {
            throw new IllegalArgumentException("[ERROR] " +  START_COMMAND + " 또는 " + END_COMMAND + "커맨드는 "
                    + START_COMMAND + " 또는 " + END_COMMAND + " 또는 " + STATUS_COMMAND + "만 입력해야 합니다.");
        }

        if (isWrongMoveCommandFormat(splitGameCommandSize, gameCommand)) {
            throw new IllegalArgumentException("[ERROR] move 명령어는 소스 위치와 타겟 위치를 모두 입력해야 합니다.");
        }
    }

    private boolean isWrongStartOrEndCommandFormat(final int splitGameCommandSize, final String gameCommand) {
        return (gameCommand.equals(START_COMMAND) || gameCommand.equals(END_COMMAND) || gameCommand.equals(STATUS_COMMAND))
                && splitGameCommandSize != CORRECT_START_END_SPLIT_SIZE;
    }

    private boolean isWrongMoveCommandFormat(final int splitGameCommandSize, final String gameCommand) {
        return gameCommand.equals(MOVE_COMMAND) && splitGameCommandSize != CORRECT_MOVE_SPLIT_SIZE;
    }

    private void validateMoveGameCommandRank(List<String> splitGameCommand, String gameCommand) {
        if (gameCommand.equals(MOVE_COMMAND)) {
            String sourcePosition = splitGameCommand.get(SOURCE_POSITION_INDEX);
            String targetPosition = splitGameCommand.get(TARGET_POSITION_INDEX);
            validateRankNumeric(sourcePosition, targetPosition);
        }
    }

    private void validateRankNumeric(String sourcePosition, String targetPosition) {
        if (isRankNotNumeric(sourcePosition) || isRankNotNumeric(targetPosition)) {
            throw new IllegalArgumentException("[ERROR] 랭크 값은 숫자여야 합니다.");
        }
    }

    private boolean isRankNotNumeric(String position) {
        List<String> splitPosition = Arrays.asList(position.split(""));
        String rank = splitPosition.get(RANK_INDEX);
        return !rank.matches(NUMERIC_REGEX);
    }
}
