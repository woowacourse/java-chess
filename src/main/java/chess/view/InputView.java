package chess.view;

import java.util.Scanner;

public class InputView {

    public static String inputCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
