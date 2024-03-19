package view;

import java.util.Scanner;

public class InputView {

    private static final String START_COMMAND = "start";
    private static final String END_COMMAND = "end";

    private final Scanner scanner = new Scanner(System.in);

    public boolean readStartCommand() {
        String command = scanner.nextLine();
        if (command.equals(START_COMMAND)) {
            return true;
        }
        if (command.equals(END_COMMAND)) {
            return false;
        }
        throw new IllegalArgumentException("잘못된 명령어입니다.");
    }
}
