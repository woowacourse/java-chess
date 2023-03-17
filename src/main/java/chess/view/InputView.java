package chess.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static String readCommand() {
        return scanner.next().trim();
    }

    public static List<String> readPositions() {
        final List<String> positions = new ArrayList<>();
        positions.add(scanner.next().trim());
        positions.add(scanner.nextLine().trim());
        return positions;
    }
}
