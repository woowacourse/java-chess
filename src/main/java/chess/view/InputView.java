package chess.view;

import java.util.Scanner;
import jdk.internal.util.xml.impl.Input;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static void printGameStartMessage() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public static String inputPlayerCommand() {
        return SCANNER.nextLine();
    }
}
