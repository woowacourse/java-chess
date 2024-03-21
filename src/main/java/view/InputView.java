package view;

import java.util.List;
import java.util.Scanner;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public List<String> enterChessCommand() {
        String input = scanner.nextLine();
        return List.of(input.split(" "));
    }
}
