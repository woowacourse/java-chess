package chess.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static String inputGameStartOrEnd() {
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
        return scanner.nextLine();
    }

    public static String inputInstruction() {
        System.out.println("\n이동: move (현위치) (대상위치)\n현재 점수: status\n끝내기: end");
        return scanner.nextLine();
    }

}
