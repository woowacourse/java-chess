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

        return readStartOrEnd();
    }

    private ChessCommand readStartOrEnd() {
        try {
            final String input = SCANNER.nextLine();
            return CommandFormat.createStartCommand(input);
        } catch (final IllegalArgumentException e) {
            System.out.println("[ERROR] " + e.getMessage());
            return readStartOrEnd();
        }
    }

    public ChessCommand readMoveCommand() {
        System.out.println();

        try {
            final String input = SCANNER.nextLine();
            return CommandFormat.createMoveCommand(input);
        } catch (final IllegalArgumentException e) {
            System.out.println("[ERROR] " + e.getMessage());
            return readMoveCommand();
        }
    }
}
