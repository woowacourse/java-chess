package view;

import static domain.classification.InputCase.*;

import domain.classification.InputCase;
import domain.position.Position;
import domain.position.XPosition;
import domain.position.YPosition;
import java.util.Scanner;

public class InputView {

    private static final Scanner sc = new Scanner(System.in);
    private static final int POSITION_SIZE = 2;
    private static final int FIRST_SINGLE_LETTER = 0;
    private static final int SECOND_SINGLE_LETTER = 1;

    public static InputCase responseUserStartCommand() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : " + START.getValue());
        System.out.println("> 게임 종료 : " + END.getValue());
        System.out.println("> 게임 점수 : " + STATUS.getValue());
        System.out.println("> 게임 이동 : " + MOVE.getValue());
        System.out.print("입력 : ");
        final String input = sc.nextLine();
        validateUserCommand(input);

        if (input.equals(START.getValue())) {
            return START;
        }
        return ELSE;
    }

    private static void validateUserCommand(final String input) {
        validateNullCheck(input);
        validateNotAllowStartCommand(input);
    }

    private static void validateNullCheck(final String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 게임 명령에 공백을 입력할 수 없습니다.");
        }
    }

    private static void validateNotAllowStartCommand(final String input) {
        if (!(input.equals(START.getValue()))) {
            throw new IllegalArgumentException("[ERROR] start 이외의 문자는 입력할 수 없습니다.");
        }
    }

    public static InputCase responseUserCommand() {
        System.out.print("입력(end, status, move) : ");
        String input = sc.nextLine();
        validateNullCheck(input);
        validateNotAllowCommand(input);
        if (input.equals(STATUS.getValue())) {
            return STATUS;
        }
        if (input.equals(MOVE.getValue())) {
            return MOVE;
        }
        if (input.equals(END.getValue())) {
            return END;
        }
        return ELSE;
    }

    private static void validateNotAllowCommand(final String input) {
        if (!(input.equals(END.getValue()) || input.equals(MOVE.getValue()) ||
                input.equals(STATUS.getValue()))) {
            String message = String.format("[ERROR] %s, %s, %s 이외의 문자는 입력할 수 없습니다.",
                    END.getValue(), MOVE.getValue(), STATUS.getValue());
            throw new IllegalArgumentException(message);
        }
    }

    public static Position responseSource() {
        System.out.print("source 위치를 입력하세요(예, b2) : ");
        String source = sc.nextLine();
        validatePositionSize(source);
        return generatePosition(source);
    }

    public static Position responseTarget() {
        System.out.print("target 위치를 입력하세요(예, b7) : ");
        String source = sc.nextLine();
        validatePositionSize(source);
        return generatePosition(source);
    }

    private static void validatePositionSize(final String source) {
        if (source.length() != POSITION_SIZE) {
            throw new IllegalArgumentException("[ERROR] 위치 입력이 잘못 입력되었습니다.");
        }
    }

    private static Position generatePosition(final String value) {
        return Position.of(
                XPosition.of(extractSingleLetter(value, FIRST_SINGLE_LETTER)),
                YPosition.of(extractSingleLetter(value, SECOND_SINGLE_LETTER))
        );
    }

    private static String extractSingleLetter(final String value, final int index) {
        return value.substring(index, index + 1);
    }
}
