package view;

import domain.command.ChessCommand;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public ChessCommand readStartCommand() {
        System.out.printf("""
                > 체스 게임을 시작합니다.
                > 게임 시작 : start
                > 게임 종료 : end
                > 게임 이동 : move source위치 target위치 - 예. move b2 b3%n""");

        final String input = SCANNER.nextLine();
        return CommandFormat.createStartCommand(input);
    }

    public ChessCommand readMoveCommand() {
        System.out.println();

        final String input = SCANNER.nextLine();
        return CommandFormat.createMoveCommand(input);
    }
}
