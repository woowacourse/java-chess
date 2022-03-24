package chess.view;

import chess.Command;
import java.util.Scanner;

public class InputView {

    private final static Scanner scanner = new Scanner(System.in);

    public static Command inputStartOrEnd() {
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
        return Command.of(scanner.nextLine());
    }
}
