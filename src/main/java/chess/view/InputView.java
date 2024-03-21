package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private final Scanner SCANNER = new Scanner(System.in);

    public List<String> readCommend() {
        String value = SCANNER.nextLine();
        validate(value);
        return Arrays.stream(value.split(" ")).toList();
    }

    private void validate(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("올바르지 않은 위치 값입니다.");
        }
    }
}
