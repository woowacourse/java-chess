package chess.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String START_COMMAND = "start";
    private static final String END_COMMAND = "end";

    public static String readStartCommand() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");

        final String command = scanner.nextLine();

        if (!command.equals(START_COMMAND) && !command.equals(END_COMMAND)) {
            throw new IllegalArgumentException("start 나 end 둘 중에 하나 입력해주세요.");
        }

        return command;
    }
}
