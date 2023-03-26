package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final String GAME_ID_MESSAGE = "게임방 목록 : ";
    private static final Scanner scanner = new Scanner(System.in);
    private static final String DELIMITER = " ";
    
    public static List<String> readCommand() {
        String input = scanner.nextLine();
        return Arrays.stream(input.split(DELIMITER)).collect(Collectors.toList());
    }

    public static int readGameId(List<Integer> ids) {
        List<String> stringIds = ids.stream().map(id -> id.toString()).collect(Collectors.toList());
        System.out.println(GAME_ID_MESSAGE + String.join(", ", stringIds));
        String input = scanner.nextLine();
        return Integer.parseInt(input);
    }
}
