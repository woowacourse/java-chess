package chess;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public String getCommand() {
        return scanner.nextLine();
    }

    public List<String> getMoveCommand() {
        return Arrays.stream(scanner.nextLine().split(" ")).toList();
    }
}
