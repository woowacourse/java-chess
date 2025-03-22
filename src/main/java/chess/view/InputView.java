package chess.view;

import chess.domain.Column;
import chess.domain.Position;
import chess.domain.Row;
import java.util.Scanner;

public class InputView {

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        System.out.println("명령어를 입력하세요(이동:MOVE 종료:Q)");
        String input = scanner.nextLine();
        validateEmptyInput(input);
        return input;
    }

    private static void validateEmptyInput(final String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("빈 입력입니다");
        }
    }

    public Position readDeparturePosition() {
        System.out.println("움직일 기물을 선택하세요(예:A1)");
        return inputPosition();
    }

    public Position readDestinationPosition() {
        System.out.println("이동할 칸을 선택하세요(예:A1)");
        return inputPosition();
    }

    private Position inputPosition() {
        String input = scanner.nextLine().strip().toUpperCase();
        validateEmptyInput(input);
        if (input.length() != 2) {
            throw new IllegalArgumentException("2글자 형식으로 입력하세요");
        }
        char column = input.charAt(0);
        char row = input.charAt(1);
        if ('A' > column || column > 'Z' || row < '1' || row > '8') {
            throw new IllegalArgumentException("맞지 않은 위치 형식입니다");
        }
        return new Position(Column.parseChar(column), Row.parseChar(row));
    }
}
