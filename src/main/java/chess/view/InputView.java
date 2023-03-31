package chess.view;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public final class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    public static final String DELIMITER = " ";
    public static final int LIMIT = -1;

    public static List<String> readCommand() {
        return Arrays.stream(scanner.nextLine().split(DELIMITER, LIMIT))
                .map(String::trim)
                .collect(toList());
    }

    public static String readGameId(final List<String> gameIds) {
        final String gameIdsDisplay = gameIds.stream().collect(Collectors.joining(", "));
        System.out.println("게임방: " + gameIdsDisplay);
        System.out.println("입장할 게임방을 선택해 주세요.");
        System.out.println("새로운 게임방을 생성할 경우, NEW를 입력해 주세요.");

        return scanner.nextLine();
    }
}
