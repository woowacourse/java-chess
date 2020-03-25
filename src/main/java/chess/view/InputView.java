package chess.view;

import java.util.Scanner;

public class InputView {
    public static final String STARTING_MSG = "체스 게임을 시작합니다.\n게임 시작은 start, 종료는 end 명령을 입력하세요.";
    private static Scanner sc = new Scanner(System.in);

    public static String inputStart() {
        System.out.println(STARTING_MSG);
        return sc.nextLine().trim();
    }
}
