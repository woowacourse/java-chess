package chess.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static Scanner scanner = new Scanner(System.in);


    public List<String> readMoveCommand() {
        List<String> moveCommand = new ArrayList<>();
        String input = scanner.nextLine();
        moveCommand.add(String.valueOf(input.charAt(5)));
        moveCommand.add(String.valueOf(input.charAt(6)));
        moveCommand.add(String.valueOf(input.charAt(8)));
        moveCommand.add(String.valueOf(input.charAt(9)));
        return moveCommand;
    }
}
