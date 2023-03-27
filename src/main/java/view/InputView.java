package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public final class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static String readStartGameOption() {
        System.out.println("> 체스 게임을 시작합니다.\n"
                + "> 게임 시작 : start\n"
                + "> 게임 종료 : end\n"
                + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3");

        return SCANNER.nextLine();
    }

    public static List<String> readPlayGameOption() {
        return Arrays.asList(SCANNER.nextLine().split(" "));
    }

    public static String readBoardName() {
        System.out.println("저장할 방 이름을 입력해주세요.");
        return SCANNER.nextLine();
    }
}
