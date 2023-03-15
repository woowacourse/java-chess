package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    
    public static final String INVALID_COMMAND_ERROR_MESSAGE = "잘못된 명령어입니다.";
    private static final String COMMAND_PROMPT = "게임 시작은 start, 종료는 end 명령을 입력하세요.";
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<String> VALID_COMMANDS = List.of("start", "move", "end");
    
    public List<String> readCommand() {
        System.out.println(COMMAND_PROMPT);
        String input = scanner.nextLine();
        List<String> tokens = Arrays.stream(input.split(" ")).collect(Collectors.toList());
        validateCommand(tokens);
        return tokens;
    }
    
    private void validateCommand(final List<String> tokens) {
        if (!VALID_COMMANDS.contains(tokens.get(0))) {
            throw new IllegalArgumentException(INVALID_COMMAND_ERROR_MESSAGE);
        }
    }
}
