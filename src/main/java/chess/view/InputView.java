package chess.view;

import java.util.Scanner;

public final class InputView {

    private InputView(){}

    private static final Scanner scanner = new Scanner(System.in);

    public static boolean readCommand() {
        System.out.println("게임 시작은" + Command.START.value() + ", 종료는" + Command.END.value() + "명령을 입력하세요.");
        return Command.from(scanner.nextLine()).getCondition();
    }
}
