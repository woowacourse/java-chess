package view;

import chess.domain.geometric.Position;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static String getStartOrEnd() {
        return SCANNER.nextLine();
    }

    public static Position getSourcePosition() {
        System.out.println("소스 위치를 입력하세요.");
        String[] numbers = SCANNER.nextLine().split(" ");

        return Position.create(Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1]));
    }

    public static Position getTargetPosition() {
        System.out.println("타깃 위치를 입력하세요.");
        String[] numbers = SCANNER.nextLine().split(" ");

        return Position.create(Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1]));
    }


}
