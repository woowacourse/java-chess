package chess.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static String readCommand() {
        System.out.println("게임 시작 : start");
        System.out.println("게임 종료 : end");
        System.out.println("게임 이동 : move source위치 target위치 - 예. move b2 b3");
        System.out.println("게임 현황 : status");
        return scanner.nextLine();
    }

    public static String readInitCommand() {
        System.out.println("이전 게임을 이어서 하시려면 continue를 입력해 주세요.");
        System.out.println("새로운 게임을 하시려면 new를 입력해 주세요.");
        return scanner.nextLine();
    }
}
