package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String INPUT_COMMAND_DELIMITER = " ";
    private final Scanner SCANNER = new Scanner(System.in);

    public List<String> requestUserCommandInGame() {
        String userCommand = SCANNER.nextLine();

        if (userCommand.isEmpty()) {
            throw new IllegalArgumentException("아무 값도 입력되지 않았습니다.");
        }
        return Arrays.asList(userCommand.split(INPUT_COMMAND_DELIMITER));
    }

    public void requestStartCommand() {
        List<String> userInputs = Arrays.asList(SCANNER.nextLine());
        if (GameCommand.from(userInputs).equals(GameCommand.START)) {
            return;
        }
        throw new IllegalArgumentException("게임 시작 명령어를 입력해주세요.");
    }

    public List<String> requestLoadGameOrNewGame() {
        List<String> userInputs = Arrays.asList(SCANNER.nextLine().split(INPUT_COMMAND_DELIMITER));
        GameCommand gameCommand = GameCommand.from(userInputs);

        if (gameCommand == GameCommand.NEW || gameCommand == GameCommand.LOAD) {
            return userInputs;
        }

        throw new IllegalArgumentException("new 또는 load (roomId)를 입력해주세요");
    }
}
