package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.util.stream.Collectors.toList;

public final class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final int START_AND_END_COMMAND_SIZE = 1;
    private static final int MOVE_COMMAND_SIZE = 3;

    public static List<String> readline() {
        final String input = scanner.nextLine().strip();
        final List<String> inputs = Arrays.stream(input.split(" ", -1))
                .map(String::strip)
                .collect(toList());

        validate(inputs);
        return inputs;
    }

    private static void validate(List<String> input) {
        if (input.size() != START_AND_END_COMMAND_SIZE && input.size() != MOVE_COMMAND_SIZE) {
            throw new IllegalStateException("안내된 명령어만 입력해주세요.");
        }
    }

}
