package chess.view;

import chess.domain.command.Command;
import chess.domain.command.CommandFactory;

import java.util.Scanner;

public class InputView {
    
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String BLANK = " ";
    private static final int COMMAND_INDEX = 0;
    
    public static Command askCommand() {
        String[] input = SCANNER.nextLine().split(BLANK);
        while (!CommandFactory.hasCommand(input[0])) {
            System.out.println("메뉴에 없는 커맨드입니다. 다시 입력해주세요.");
            input = SCANNER.nextLine().split(BLANK);
        }
        
        System.out.println();
        
        return CommandFactory.createFrom(input[COMMAND_INDEX]);
    }
}
