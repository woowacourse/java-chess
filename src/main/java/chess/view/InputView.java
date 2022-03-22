package chess.view;

import java.util.Scanner;

import chess.Command;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static Command inputCommand() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");

        return Command.of(SCANNER.nextLine());
    }

}
