package chess.view;

import chess.controller.command.CommandController;
import chess.controller.command.CommandControllerFactory;

import java.util.Scanner;

public class InputView {
    
    private static final Scanner SCANNER = new Scanner(System.in);
    
    public static CommandController askCommand() {
        String input = SCANNER.nextLine();
        while (!CommandControllerFactory.hasCommand(input)) {
            System.out.println("메뉴에 없는 커맨드입니다. 다시 입력해주세요.");
            input = SCANNER.nextLine();
        }
        
        System.out.println();
        
        return CommandControllerFactory.createFrom(input);
    }
}
