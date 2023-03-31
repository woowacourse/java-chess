package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public final class InputView {
    private static final String NOT_EXIST_COMMAND = "명령어를 입력해야 합니다.";

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String NOT_A_NUMBER = "숫자를 입력해야 합니다.";

    public static String readStartOption() {
        System.out.println("> 체스 게임을 시작합니다.\n"
                + "> 게임 시작 : start\n"
                + "> 게임 불러오기 : load\n"
                + "> 게임 종료 : end\n"
                + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3\n"
                + "> 현재 상태 : status");

        return SCANNER.nextLine();
    }

    public static int readRoomNumber() {
        System.out.println("원하는 방 번호를 입력하세요.");
        try {
            return Integer.parseInt(SCANNER.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_A_NUMBER);
        }
    }

    public static String readRoomName() {
        System.out.println("생성할 방 이름을 1 ~ 20 길이로 입력해주세요.");
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
