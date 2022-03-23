package chess.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public boolean inputStartCommand() {
        String input = SCANNER.nextLine();

        if (input.equals("start")) {
            return true;
        }

        if (input.equals("end")) {
            return false;
        }

        return false;
    }
}
