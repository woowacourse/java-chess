package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public String readCommand() {
        return scanner.nextLine();
    }

    public List<String> readMoveCommand() {
        return Arrays.stream(scanner.nextLine().split(" ")).toList();
    }
}
