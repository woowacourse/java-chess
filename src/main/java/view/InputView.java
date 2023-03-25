package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public final class InputView {
    private static final String NOT_EXIST_COMMAND = "명령어를 입력해야 합니다.";

    private static final Scanner SCANNER = new Scanner(System.in);

    public static String readStartOption() {
        System.out.println("> 체스 게임을 시작합니다.\n"
                + "> 게임 시작 : start\n"
                + "> 게임 종료 : end\n"
                + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3");

        return SCANNER.nextLine();
    }

    public static List<String> readPlayGameOption() {
        final String[] options = SCANNER.nextLine().split(" ");

        if (options.length == 0) {
            throw new IllegalArgumentException(NOT_EXIST_COMMAND);
        }

        return Arrays.asList(options);
    }
}
