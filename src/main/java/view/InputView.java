package view;

import domain.command.ChessCommand;
import domain.command.EndCommand;
import domain.command.MoveCommand;
import domain.command.StartCommand;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String START_COMMAND = "start";
    private static final String END_COMMAND = "end";
    private static final String MOVE_COMMAND = "move";
    private static final String MOVE_COMMAND_DELIMITER = " ";
    private static final int MOVE_COMMAND_SIZE = 3;

    public ChessCommand readStartCommand() {
        System.out.printf("""
                > 체스 게임을 시작합니다.
                > 게임 시작 : %s
                > 게임 종료 : %s
                > 게임 이동 : %s source위치 target위치 - 예. move b2 b3%n""", START_COMMAND, END_COMMAND, MOVE_COMMAND);

        final String input = SCANNER.nextLine();

        if (END_COMMAND.equals(input)) {
            return new EndCommand();
        }
        if (START_COMMAND.equals(input)) {
            return new StartCommand();
        }
        throw new IllegalArgumentException("잘못된 커맨드입니다.");
    }

    public ChessCommand readMoveCommand() {
        System.out.println();

        final String input = SCANNER.nextLine();

        if (END_COMMAND.equals(input)) {
            return new EndCommand();
        }

        final List<String> inputs = Arrays.asList(input.split(MOVE_COMMAND_DELIMITER));
        validateMoveCommand(inputs);

        final Iterator<String> moveCommands = inputs.iterator();
        if (MOVE_COMMAND.equals(moveCommands.next())) {
            return new MoveCommand(moveCommands.next(), moveCommands.next());
        }

        throw new IllegalArgumentException("잘못된 커맨드입니다.");
    }

    private void validateMoveCommand(final List<String> inputs) {
        if (inputs.size() != MOVE_COMMAND_SIZE) {
            throw new IllegalArgumentException("잘못된 MOVE 커맨드 입니다.");
        }
    }
}
