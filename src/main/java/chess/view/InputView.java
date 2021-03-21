package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static List<String> requestPositions() {
        String userInput = scanner.nextLine();
        List<String> inputs = Arrays.asList(userInput.split(" "));
        if (inputs.size() == 3 && "move".equals(inputs.get(0))) {
            return inputs.subList(1,3);
        }
        throw new IllegalArgumentException("잘못된 입력입니다.");
    }

    public static boolean willNotPlayGame() {
        String userInput = scanner.nextLine();
        if ("start".equals(userInput)) {
            return false;
        }
        if ("end".equals(userInput)) {
            return true;
        }
        throw new IllegalArgumentException("start 또는 end 를 입력해주세요");
    }

    public static boolean willWatchScore() {
        String userInput = scanner.nextLine();
        if ("status".equals(userInput)) {
            return true;
        }
        if ("end".equals(userInput)) {
            return false;
        }
        throw new IllegalArgumentException("status 또는 end 를 입력해주세요");
    }
}
