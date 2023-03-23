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

    public String requestUserCommandInGame() {
        String userCommand = SCANNER.nextLine();

        if (userCommand.isEmpty()) {
            throw new IllegalArgumentException("아무 값도 입력되지 않았습니다.");
        }

        List<String> userCommands = Arrays.asList(userCommand.split(INPUT_COMMAND_DELIMITER));
        if (GameCommand.from(userCommands.get(COMMAND_INDEX)).equals(GameCommand.END)) {
            return userCommand;
        }

        if (userCommands.size() == 1 && userCommands.get(0).equals("status")) {
            return userCommand;
        }
        if (userCommands.size() != MOVE_COMMAND_INPUT_CORRECT_SIZE) {
            throw new IllegalArgumentException("이동 입력은 move b2 b3 형식으로 입력해주세요.");
        }

        if (!GameCommand.from(userCommands.get(COMMAND_INDEX)).equals(GameCommand.MOVE)) {
            throw new IllegalArgumentException("이동 입력은 move b2 b3 형식으로 입력해주세요.");
        }

        return userCommands.get(SOURCE_POSITION_INDEX) + userCommands.get(TARGET_POSITION_INDEX);
    }

    public void requestStartCommand() {
        if (GameCommand.from(SCANNER.nextLine()).equals(GameCommand.START)) {
            return;
        }
        throw new IllegalArgumentException("게임 시작 명령어를 입력해주세요.");
    }
}
