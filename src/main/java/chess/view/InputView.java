package chess.view;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final String DELIMITER = " ";

    private final Scanner scanner;

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public String inputStart() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
        return scanner.nextLine();
    }

    public Queue<String> inputCommand() {
        String input = scanner.nextLine();
        Queue<String> inputs = Arrays.stream(input.split(DELIMITER))
                .collect(Collectors.toCollection(ArrayDeque::new));
        validateInputs(inputs);
        return inputs;
    }

    private void validateInputs(final Queue<String> inputs) {
        if (inputs.size() != 1 && inputs.size() != 3) {
            throw new IllegalArgumentException("유효하지 않은 입력입니다!");
        }
    }
}
