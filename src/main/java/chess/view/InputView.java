package chess.view;

import chess.dto.request.CommandDto;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

public final class InputView {

    private static final String DELIMITER = " ";
    private static final Scanner scanner = new Scanner(System.in);

    private static final Set<Integer> COMMAND_NECESSARY_WORD = Set.of(1, 3);

    private InputView() {
    }

    public static CommandDto readInitialCommand() {
        List<String> userInput = readUserInput();
        GameCommand gameCommand = recognizeGameCommand(userInput);
        while (gameCommand == GameCommand.MOVE) {
            OutputView.printNotStartedGameMessage();
            userInput = readUserInput();
            gameCommand = recognizeGameCommand(userInput);
        }
        return insertIntoCommandDto(userInput);
    }

    private static List<String> readUserInput() {
        String input = scanner.nextLine();
        return List.of(input.split(DELIMITER));
    }

    private static GameCommand recognizeGameCommand(List<String> input) {
        return GameCommand.of(input.get(0));
    }

    public static CommandDto readPlayingCommand() {
        List<String> userInput = readUserInput();
        validateInput(userInput);
        return insertIntoCommandDto(userInput);
    }

    private static void validateInput(List<String> userInput) {
        if (!COMMAND_NECESSARY_WORD.contains(userInput.size())) {
            throw new IllegalArgumentException("[ERROR] 명령어 형식이 올바르지 않습니다.");
        }
        GameCommand gameCommand = recognizeGameCommand(userInput);
        if (gameCommand == GameCommand.START) {
            throw new IllegalArgumentException("[ERROR] 게임 진행 중에는 move와 end 명령어만 입력 가능합니다.");
        }
    }

    private static CommandDto insertIntoCommandDto(List<String> userInput) {
        GameCommand gameCommand = recognizeGameCommand(userInput);
        String startPosition = null;
        String endPosition = null;
        if (userInput.size() == 3) {
            startPosition = userInput.get(1);
            endPosition = userInput.get(2);
        }
        return CommandDto.of(gameCommand, startPosition, endPosition);
    }
}
