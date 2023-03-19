package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    public static final String DELIMITER = " ";

    public List<String> readCommand() {
        String input = scanner.nextLine();
        return Arrays.stream(input.split(DELIMITER, -1))
                .map(String::strip)
                .collect(Collectors.toList());
    }

}
