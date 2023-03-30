package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String GAME_COMMAND_DELIMITER = " ";
    private static final int GAME_COMMAND_INDEX = 0;
    private static final int CORRECT_START_END_SPLIT_SIZE = 1;
    private static final int CORRECT_MOVE_SPLIT_SIZE = 3;

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public List<String> inputGameCommand() {
        String input = scanner.nextLine();
        final List<String> splitGameCommand = Arrays.asList(input.split(GAME_COMMAND_DELIMITER));
        final String gameCommand = splitGameCommand.get(GAME_COMMAND_INDEX);

        validateGameCommandInput(gameCommand);
        validateGameCommandFormat(splitGameCommand);

        return splitGameCommand;
    }

    private void validateGameCommandInput(final String gameCommand) {
        if (isWrongGameCommand(gameCommand)) {
            throw new IllegalArgumentException(("[ERROR] \"start\", \"end\", \"move\"만 입력해주세요."));
        }
    }

    private boolean isWrongGameCommand(final String gameCommand) {
        return !gameCommand.equals(GameCommand.START.getText()) &&
                !gameCommand.equals(GameCommand.END.getText()) &&
                !gameCommand.equals(GameCommand.MOVE.getText());
    }

    private void validateGameCommandFormat(final List<String> splitGameCommand) {
        final String gameCommand = splitGameCommand.get(GAME_COMMAND_INDEX);
        final int splitGameCommandSize = splitGameCommand.size();
        if (isWrongStartOrEndCommandFormat(splitGameCommandSize, gameCommand)) {
            throw new IllegalArgumentException("[ERROR] \"start\" 또는 \"end\" 커맨드는 \"start\" 또는 \"end\"만 입력해야 합니다.");
        }

        if (isWrongMoveCommandFormat(splitGameCommandSize, gameCommand)) {
            throw new IllegalArgumentException("[ERROR] move 명령어는 소스 위치와 타겟 위치를 모두 입력해야 합니다.");
        }
    }

    private boolean isWrongStartOrEndCommandFormat(final int splitGameCommandSize, final String gameCommand) {
        return (gameCommand.equals(GameCommand.START.getText()) || gameCommand.equals(GameCommand.END.getText()))
                && splitGameCommandSize != CORRECT_START_END_SPLIT_SIZE;
    }

    private boolean isWrongMoveCommandFormat(final int splitGameCommandSize, final String gameCommand) {
        return gameCommand.equals(GameCommand.MOVE.getText()) && splitGameCommandSize != CORRECT_MOVE_SPLIT_SIZE;
    }

    public String inputCommandAfterGameEnd() {
        final String input = scanner.nextLine();
        validateCommandAfterGameEnd(input);
        return input;
    }

    private void validateCommandAfterGameEnd(final String input) {
        if (isWrongEndOrStatusCommandFormat(input)) {
            throw new IllegalArgumentException("[ERROR] 잘못된 명령어입니다.");
        }
    }

    private boolean isWrongEndOrStatusCommandFormat(final String input) {
        return !(input.equals(GameCommand.STATUS.getText()) || input.equals(GameCommand.END.getText()));
    }

    public String inputGameStartCommand() {
        final String input = scanner.nextLine();
        validateGameStartCommandFormat(input);
        return input;
    }

    private void validateGameStartCommandFormat(final String input) {
        if (isWrongGameStartCommandFormat(input)) {
            throw new IllegalArgumentException("[ERROR] \"new\" 또는 \"load\" 명령어만 입력 가능합니다.");
        }
    }

    private boolean isWrongGameStartCommandFormat(final String input) {
        return !(input.equals(GameCommand.NEW.getText()) || input.equals(GameCommand.LOAD.getText()));
    }
}
