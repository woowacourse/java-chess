package chess.view;

import chess.Column;
import chess.Position;
import chess.Row;
import java.util.Scanner;

public class InputView {
    private static final Scanner sc = new Scanner(System.in);

    //TODO 입력값 예외 처리
    public static Position readMovingPosition() {
        System.out.println("\n움직일 기물의 열과 행을 선택해주세요. (ex. a,1)");
        String[] input = sc.nextLine().split(",");
        return new Position(
                Column.valueOf(input[0].toUpperCase()),
                Row.fromNumber(Integer.parseInt(input[1]))
        );
    }

    public static Position readTargetPosition() {
        System.out.println("어느 열과 행으로 움직일지 선택해주세요. (ex. b,2)");
        String[] input = sc.nextLine().split(",");
        return new Position(
                Column.valueOf(input[0].toUpperCase()),
                Row.fromNumber(Integer.parseInt(input[1]))
        );
    }
}
