package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public List<String> inputGameCommand() {
        return Arrays.asList(scanner.nextLine().split(" ", -1));
    }
}
