package chess.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static String inputCommand() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("=== 게임 시작 후 가능한 명령 ===");
        System.out.println("> 게임 이동 : move source위치 target위치 - ex) move b2 b3");
        System.out.println("> 점수 출력 : status");
        System.out.println("===========================");
        return input();
    }

    public static String input() {
        return scanner.nextLine();
    }
}
