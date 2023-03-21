package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public List<String> readExecuteCommands() {
        return Arrays.stream(scanner.nextLine().split(" ", -1))
                .collect(Collectors.toList());
    }
}
