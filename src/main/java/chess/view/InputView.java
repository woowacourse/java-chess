package chess.view;

import static chess.domain.classification.CommandCase.*;

import chess.domain.classification.Command;
import chess.domain.classification.CommandCase;
import java.util.Scanner;

public final class InputView {

    private static final Scanner sc = new Scanner(System.in);

    public static Command responseUserStartCommand() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : " + START.getValue());
        System.out.println("> 게임 종료 : " + END.getValue());
        System.out.println("> 게임 점수 : " + STATUS.getValue());
        System.out.println("> 게임 이동 : " + MOVE.getValue() + " source위치 target위치 - 예. move b2 b3");
        final String input = sc.nextLine();
        validateNullCheck(input);
        Command.validateStartCommand(input);

        if (checkStart(input)) {
            return Command.of(START);
        }
        return Command.of(ELSE);
    }

    private static void validateNullCheck(final String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 게임 명령에 공백을 입력할 수 없습니다.");
        }
    }

    public static Command responseUserCommand() {
        String input = sc.nextLine();
        validateNullCheck(input);
        if (CommandCase.checkEnd(input)) {
            return Command.of(END);
        }
        if (CommandCase.checkStatus(input)) {
            return Command.of(STATUS);
        }
        if (CommandCase.checkMove(input)) {
            return Command.of(MOVE, input);
        }
        return Command.of(ELSE);
    }
}
