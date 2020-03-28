package chess.views;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String DELIMITER = " ";
    private static final String COMMAND = "command";
    private static final String FROM = "from";
    private static final String TO = "to";

    public static Map<String, String> inputCommand() {
        String string = scanner.nextLine();
        Map<String, String> stringMap = new HashMap<>();
        if (string.split(DELIMITER).length == 1) {
            stringMap.put(COMMAND, string);
            return stringMap;
        }
        stringMap.put(COMMAND, string.split(DELIMITER)[0]);
        stringMap.put(FROM, string.split(DELIMITER)[1]);
        stringMap.put(TO, string.split(DELIMITER)[2]);
        return stringMap;
    }
}
