package chess.view;

import java.util.Scanner;

public final class InputView {
    
    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {}
    
    public static String askCommand() {
        String input = SCANNER.nextLine();
        
        System.out.println();
        
        return input;
    }
}
