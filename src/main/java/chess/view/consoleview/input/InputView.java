package chess.view.consoleview.input;

import java.util.Scanner;

public abstract class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static String inputCommand() {
        return scanner.nextLine();
    }

    public static String inputPromotionType() {
        System.out.println("폰을 어떤 기물로 프로모션 하시겠습니다?%n퀸(q), 룩(r), 비숍(b), 나이트(n)");
        return inputCommand();
    }
}
