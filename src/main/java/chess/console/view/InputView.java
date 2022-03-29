package chess.console.view;

import java.util.Scanner;

public class InputView {

    public static final int MENU_INDEX = 0;
    public static final int FROM_POSITION_INDEX = 1;
    public static final int TO_POSITION_INDEX = 2;
    private static final int COMMAND_MIN_COUNT = 1;
    private static final int COMMAND_MAX_COUNT = 3;
    private static final String SEPERATOR = " ";
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static String[] inputCommand() {
        try {
            String[] inputs = scanner.nextLine().split(SEPERATOR, -1);
            validInputs(inputs);
            return inputs;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputCommand();
        }
    }

    private static void validInputs(String[] inputs) {
        int size = inputs.length;
        if (size != COMMAND_MIN_COUNT && size != COMMAND_MAX_COUNT) {
            throw new IllegalArgumentException("잘못된 명령어를 입력하셨습니다.");
        }
    }
}
