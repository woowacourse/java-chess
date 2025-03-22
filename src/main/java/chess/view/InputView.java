package chess.view;

import chess.Column;
import chess.Position;
import chess.Row;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static Position readStartPosition() {
        System.out.println("출발 좌표를 입력해주세요.(예: A,8)");
        String input = scanner.nextLine();
        String[] split = input.split(",");
        return new Position(Column.valueOf(split[0]), Row.values()[8-Integer.parseInt(split[1])]);
    }

    public static Position readEndPosition() {
        System.out.println("도착 좌표를 입력해주세요.(예: A,8)");
        String input = scanner.nextLine();
        String[] split = input.split(",");
        return new Position(Column.valueOf(split[0]), Row.values()[8-Integer.parseInt(split[1])]);
    }
}
