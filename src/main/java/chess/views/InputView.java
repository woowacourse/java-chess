package chess.views;

import java.util.Scanner;

public class InputView {
    private final static Scanner scanner = new Scanner(System.in);

    public static String inputCommand(){
        return scanner.nextLine();
    }
}
