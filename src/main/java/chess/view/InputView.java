package chess.view;

import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String DELIMITER = " ";
    private static final int LIMIT = -1;

    private InputView() {
    }

    public static List<String> readCommand() {
        List<String> command = List.of(scanner.nextLine().split(DELIMITER, LIMIT));
        System.out.println();

        return command;
    }

    public static String readPlayerName() {
        System.out.printf("참여자 이름을 입력해주세요 : ");
        String name = scanner.nextLine();
        System.out.println();

        return name;
    }
}
