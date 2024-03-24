package chess.view;

import chess.controller.Command;
import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public Command readCommand() {
        String input = SCANNER.next();
        return Command.of(input);
    }

    public String readLocation() {
        return SCANNER.next();
    }

    public boolean checkRestartGame() {
        System.out.println("정말 게임을 재시작 하겠습니까? (y/n)");
        String input = SCANNER.next();
        return "y".equalsIgnoreCase(input);
    }
}
