package view;

import domain.GameCommand;

import java.util.Scanner;

public final class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static GameCommand readGameCommand() {
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
        return GameCommand.of(scanner.nextLine());
    }
}
