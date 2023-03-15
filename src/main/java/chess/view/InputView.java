package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    
    private static final String COMMAND_PROMPT = "게임 시작은 start, 종료는 end 명령을 입력하세요.";
    private static final Scanner scanner = new Scanner(System.in);
    
    public List<String> readCommand() {
        System.out.println(COMMAND_PROMPT);
        String input = scanner.nextLine();
        return Arrays.stream(input.split(" ")).collect(Collectors.toList());
    }
}
