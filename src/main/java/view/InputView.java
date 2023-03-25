package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String INPUT_COMMAND_DELIMITER = " ";
    private static final int MOVE_COMMAND_INPUT_CORRECT_SIZE = 3;
    private static final int COMMAND_INDEX = 0;
    private static final int SOURCE_POSITION_INDEX = 1;
    private static final int TARGET_POSITION_INDEX = 2;
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

    public GameCommand requestLoadGameOrNewGame() {
        List<String> userInputs = Arrays.asList(SCANNER.nextLine());
        GameCommand gameCommand = GameCommand.from(userInputs);

        if (gameCommand == GameCommand.NEW || gameCommand == GameCommand.LOAD) {
            return gameCommand;
        }

        throw new IllegalArgumentException("새로운 게임 시작 : 'new'아니면 기존 게임 불러오기 : 'load'를 입력해주세요.");
    }
}
