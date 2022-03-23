package chess.view.input;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
        throw new AssertionError();
    }

    public static Command inputCommand() {
        System.out.println("체스 게임을 시작합니다.\n 게임 시작은 start, 종료는 end 명령을 입력하세요.");
        return Command.from(scanner.nextLine());
    }
}
