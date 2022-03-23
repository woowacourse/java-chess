package chess.view;

import chess.Command;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static Command requestCommand() {
        System.out.print("체스 게임을 시작합니다.\n" +
                "게임 시작은 start, 종료는 end 명령을 입력하세요.");
        return Command.of(SCANNER.nextLine());
    }
}
