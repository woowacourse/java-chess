package chess.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static String inputStartOrEndGame() {
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
        String command = SCANNER.nextLine();
        checkCommandValue(command);
        return command;
    }

    private static void checkCommandValue(String inputCommand) {
        if (!inputCommand.equals("start") && !inputCommand.equals("end")) {
            throw new IllegalArgumentException(String.format("잘못된 게임 시작 커맨드입니다. %s", inputCommand));
        }
    }
}
