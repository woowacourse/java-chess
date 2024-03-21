package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class InputView {
    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    public static final String POSITION_REGEX = "^[a-h][1-8]$";

    private InputView() {
    }

    public static GameCommand inputInitialGameCommand() {
        List<String> inputCommand = Arrays.asList(readLine().split(" "));
        GameCommand initialGameCommand = GameCommand.from(inputCommand);
        if (initialGameCommand == GameCommand.MOVE) {
            throw new IllegalArgumentException("게임을 시작해야 이동 명령을 입력할 수 있습니다.");
        }
        return initialGameCommand;
    }

    public static List<String> parseCommand() {
        List<String> inputMovement = Arrays.asList(readLine().split(" "));
        GameCommand gameCommand = GameCommand.from(inputMovement);
        if (gameCommand == GameCommand.MOVE) {
            validateInputMovement(inputMovement);
        }
        return inputMovement;
    }

    private static void validateInputMovement(List<String> inputMovement) {
        if (inputMovement.size() == 3) {
            inputMovement.subList(1, inputMovement.size())
                    .stream()
                    .filter(position -> !position.matches(POSITION_REGEX))
                    .findAny()
                    .ifPresent(position -> {
                        throw new IllegalArgumentException("입력하신 " + position + "은 올바르지 않은 좌표입니다");
                    });
        }
    }

    private static String readLine() {
        try {
            return READER.readLine();
        } catch (final IOException exception) {
            System.out.println(exception.getMessage());
            return readLine();
        }
    }
}
