package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    public static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static List<String> command() {
        return Arrays.asList(SCANNER.nextLine().split(" "));
    }

}
