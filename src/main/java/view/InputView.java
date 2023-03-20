package view;

import domain.GameCommand;

import java.util.*;

public final class InputView {

    public static final String DELIMITER = " ";
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static GameCommand readInitialCommand() {
        OutputView.printStartMessage();
        GameCommand gameCommand = recognizeGameCommand(readUserInput());
        while (gameCommand == GameCommand.MOVE) {
            OutputView.printNotStartedGameMessage();
            gameCommand = recognizeGameCommand(readUserInput());
        }
        return gameCommand;
    }

    private static List<String> readUserInput() {
        String input = scanner.nextLine();
        return List.of(input.split(DELIMITER));
    }

    private static GameCommand recognizeGameCommand(List<String> input) {
        return GameCommand.of(input.get(0));
    }

    public static List<String> readPlayingCommand() {
        List<String> input = readUserInput();
        if (input.size() != 1 && input.size() != 3) {
            throw new IllegalArgumentException("[ERROR] 명령어 형식이 올바르지 않습니다.");
        }
        GameCommand gameCommand = recognizeGameCommand(input);
        if (gameCommand == GameCommand.START) {
            throw new IllegalArgumentException("[ERROR] 게임 진행 중에는 move와 end 명령어만 입력 가능합니다.");
        }
        return input;
    }
}
