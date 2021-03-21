package view;

import java.util.Scanner;

public class InputView {
    private static Scanner scanner = new Scanner(System.in);

    private InputView() {

    }

    public static String inputCommand() {
        return scanner.nextLine();
    }
}
