package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private final Scanner scanner;

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public List<String> readCommand() {
        final String[] split = scanner.nextLine()
                                      .split(" ");
        List<String> command = Arrays.asList(split);

        return command.stream()
                      .map(String::strip)
                      .collect(Collectors.toUnmodifiableList());
    }

    public int readGameId() {
        System.out.println("게임 id를 입력하세요");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("게임 아이디는 정수로 입력해주세요");
        }
    }
}
