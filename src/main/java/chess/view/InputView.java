package chess.view;

import java.util.Scanner;

public class InputView {
    public static final Scanner scanner = new Scanner(System.in);

    public String readGameStart() {
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요");
        return scanner.nextLine();
    }
}
