package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    
    private static final Scanner scanner = new Scanner(System.in);
    private static final String DELIMITER = " ";
    
    public static List<String> readCommand() {
        String input = scanner.nextLine();
        return Arrays.stream(input.split(DELIMITER)).collect(Collectors.toList());
    }
}
