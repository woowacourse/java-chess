package chess.view;

import java.util.Scanner;

public class InputView {

    public static String startGame() {
        System.out.println("체스 게임을 시작합니다.\n게임 시작은 start, 종료는 end 명령을 입력하세요.");
        return inputCommand();
    }

    public static String inputCommand() {
        return new Scanner(System.in).nextLine();
    }
}
