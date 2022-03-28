package chess.view.input;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String COMMAND_SPLIT_DELIMITER = " ";

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
        throw new AssertionError();
    }

    public static List<String> inputCommand() {
        return Arrays.stream(scanner.nextLine().split(COMMAND_SPLIT_DELIMITER))
                .collect(Collectors.toList());
    }

    public static String inputPromotionType() {
        System.out.println("이동한 pawn이 promotion 가능합니다. 어떤 기물로 promotion 하시겠습니까?"
                + "%n 퀸:q, 나이트:n, 룩:r, 비숍:b");
        return scanner.nextLine();
    }
}
