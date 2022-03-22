package chess.view;

import chess.GameSwitch;
import java.util.Scanner;

public class InputView {

    private final static Scanner scanner = new Scanner(System.in);

    public static GameSwitch inputStartOrEnd() {
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
        return GameSwitch.of(scanner.nextLine());
    }
}
