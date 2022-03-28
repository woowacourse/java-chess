package chess.view;

import java.util.Scanner;

public class InputView {

    public static final int MENU_INDEX = 0;
    public static final int FROM_POSITION_INDEX = 1;
    public static final int TO_POSITION_INDEX = 2;
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static String[] inputCommand() {
        try {
            String[] inputs = scanner.nextLine().split(" ", -1);
            validInputs(inputs);
            return inputs;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputCommand();
        }
    }

    private static void validInputs(String[] inputs) {
        int size = inputs.length;
        if (size != 1 && size != 3) {
            throw new IllegalArgumentException("잘못된 명령어를 입력하셨습니다.");
        }
    }
}
