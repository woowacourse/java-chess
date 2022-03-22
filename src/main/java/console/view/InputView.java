package console.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static String inputCommand() {
        String command = SCANNER.nextLine();
        if (command.equals("start") || command.equals("end")) {
            return command;
        }
        throw new IllegalArgumentException("[ERROR] 잘못된 명령어를 입력하셨습니다.");
    }
}
