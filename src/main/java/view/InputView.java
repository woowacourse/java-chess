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
        InputCase.validateUserCommand(input);

        if (InputCase.checkStart(input)) {
            return START;
        }
        return ELSE;
    }

    public static InputCase responseUserCommand() {
        System.out.print("입력(end, status, move) : ");
        String input = sc.nextLine();
        return InputCase.responseUserCommand(input);
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
