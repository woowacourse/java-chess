package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> readline() {
        final String input = scanner.nextLine().strip();
        final List<String> inputs = Arrays.stream(input.split(" ", -1))
                .map(String::strip)
                .collect(Collectors.toList());

        validate(inputs);
        return inputs;
    }

    private static void validate(List<String> input) {
        if (input.size() < 1 || input.size() > 3) {
            throw new IllegalStateException("안내된 명령어만 입력해주세요.");
        }
    }

}
