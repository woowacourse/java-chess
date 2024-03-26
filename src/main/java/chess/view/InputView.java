package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static Scanner scanner = new Scanner(System.in);

    public static String readCommand() {
        return scanner.next();
    }

    public static List<String> readPosition() {
        List<String> position = Arrays.stream(scanner.next()
                        .trim()
                        .split(""))
                .toList();

        if(position.size() != 2) {
            throw new IllegalArgumentException("좌표는 2글자여야 합니다.");
        }

        return position;
    }
}
