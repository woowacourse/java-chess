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
        if ("y".equalsIgnoreCase(input)){
            return true;
        }
        if ("n".equalsIgnoreCase(input)) {
            return false;
        }
        throw new IllegalArgumentException(
                String.format("잘못된 입력입니다. y 또는 n을 입력해 주세요 (현재 입력 : %s)", input)
        );
    }

}
