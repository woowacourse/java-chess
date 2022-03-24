package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private final static Scanner scanner = new Scanner(System.in);

    public static List<String> inputCommand() {
        return Arrays.stream(scanner.nextLine().split(" "))
                .collect(Collectors.toList());
    }
}
