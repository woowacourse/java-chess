package chess.view;

import java.util.Scanner;

import static chess.view.InputValidator.*;

public class GameConnectionInputView {

    private final Scanner scanner = new Scanner(System.in);

    public String readUserId() {
        String input = scanner.nextLine();
        validateUserIdLength(input);
        return input;
    }

    public int readRoomId() {
        String input = scanner.nextLine();
        validateIsNumeric(input);
        return Integer.parseInt(input);
    }

//    public boolean doNotSave() {
//        String input = scanner.nextLine();
//        validateAnswer(input);
//        return NO.equals(input);
//    }
}
