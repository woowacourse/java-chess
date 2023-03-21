package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String POSITION_FORMAT_VALIDATE_MESSAGE = "이동할 위치를 형식에 맞게 입력해주십시오. ex : b2 b4";

    public static String readCommand() {
        return scanner.next().trim();
    }

    public static List<String> readPositions() {
        final List<String> positions = Arrays.stream(scanner.nextLine().trim()
                .split(" ", -1)).collect(Collectors.toList());
        validatePositionsSize(positions);
        return positions;
    }

    public static void clearBuffer() {
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }

    private static void validatePositionsSize(final List<String> positions) {
        if (positions.size() != 2) {
            throw new IllegalArgumentException(POSITION_FORMAT_VALIDATE_MESSAGE);
        }
    }
}
