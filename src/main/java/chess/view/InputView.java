package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final String ROOM_ID_MESSAGE = "입장하고자 하는 방 ID를 입력해주세요.";
    private static final Scanner scanner = new Scanner(System.in);
    private static final String DELIMITER = " ";
    
    public static List<String> readCommand() {
        String input = scanner.nextLine();
        return Arrays.stream(input.split(DELIMITER)).collect(Collectors.toList());
    }

    public static int readGameId() {
        System.out.println(ROOM_ID_MESSAGE);
        String input = scanner.nextLine();
        return Integer.parseInt(input);
    }
}
