package chess.ui;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.util.stream.Collectors.toList;

public final class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static List<String> getCommands() {
        return Arrays.stream(SCANNER.nextLine().split(" "))
                .collect(toList());
    }

}
