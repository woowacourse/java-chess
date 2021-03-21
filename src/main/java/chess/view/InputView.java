package chess.view;

import chess.controller.command.CommandController;
import chess.controller.command.CommandControllerFactory;

import java.util.Scanner;

public class InputView {
    
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String BLANK = " ";
    
    public static CommandController askCommand() {
        String input = SCANNER.next();
        while (!CommandControllerFactory.hasCommand(input)) {
            System.out.println("메뉴에 없는 커맨드입니다. 다시 입력해주세요.");
            removeExtraInput();
            input = SCANNER.next();
        }
        
        System.out.println();
        
        return CommandControllerFactory.createFrom(input);
    }
    
    private static void removeExtraInput() {
        SCANNER.nextLine();
    }
    
    public static String[] getPositionsRemainingAtConsole() {
        return SCANNER.nextLine().split(BLANK);
    }
}
