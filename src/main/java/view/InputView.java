package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dto.CommandRequestDto;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String DELIMITER = " ";

    private InputView() {
    }

    public static CommandRequestDto requestCommand() {
        String input = scanner.nextLine();
        validate(input);
        return new CommandRequestDto(Arrays.asList(input.split(DELIMITER)));
    }

    private static void validate(String input) {
        if (input.isEmpty() || input.isBlank()) {
            throw new IllegalArgumentException("명령을 입력해주세요.");
        }
    }
}
