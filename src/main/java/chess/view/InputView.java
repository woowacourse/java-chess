package chess.view;

import java.util.Scanner;

public class InputView {

    private final static Scanner scanner = new Scanner(System.in);

    public static String inputStartOrEnd() {
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
        return scanner.nextLine();
    }
}
