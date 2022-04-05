package chess.console.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String INPUT_NAME_MESSAGE = "게임 진행을 위해 닉네임을 입력해 주세요.";
    private static final String NAME_CONTAIN_BLANK_ERROR = "이름에는 공백이 포함될 수 없습니다.";
    private static final String BLANK = " ";

    public static String inputName() {
        try {
            System.out.println(INPUT_NAME_MESSAGE);
            final String name = input();
            validateName(name);
            return name;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputName();
        }
    }

    public static String input() {
        return scanner.nextLine();
    }

    private static void validateName(String name) {
        if (name.contains(BLANK)) {
            throw new IllegalArgumentException(NAME_CONTAIN_BLANK_ERROR);
        }
    }
}
