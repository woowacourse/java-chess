package view;

import common.ExecuteContext;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String INVALID_INPUT_ERROR_MESSAGE = "입력이 잘못 되었습니다. 다시 입력해 주세요.";
    private static final Scanner scanner = new Scanner(System.in);
    private static final String START = "start";
    private static final String END = "end";

    public boolean getEndIntent() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
        final String order = ExecuteContext.repeatableExecute(() -> {
            final String input = scanner.nextLine();
            checkExpectedInput(input, START, END);
            return input;
        });
        return order.equals(END);
    }

    public List<String> getCommand() {
        final String[] inputs = scanner.nextLine().split(" ");
        if (inputs.length > 3) {
            throw new IllegalArgumentException(INVALID_INPUT_ERROR_MESSAGE);
        }
        return Arrays.stream(inputs).collect(Collectors.toList());
    }

    private void checkExpectedInput(final String input, final String... expectedInputs) {
        checkBlank(input);
        if (Arrays.asList(expectedInputs).contains(input)) {
            return;
        }
        throw new IllegalArgumentException(INVALID_INPUT_ERROR_MESSAGE);
    }

    private void checkBlank(final String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException(INVALID_INPUT_ERROR_MESSAGE);
        }
    }
}
