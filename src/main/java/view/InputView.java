package view;

import static domain.classification.OrderCase.*;

import domain.classification.Order;
import domain.classification.OrderCase;
import java.util.Scanner;

public class InputView {

    private static final Scanner sc = new Scanner(System.in);

    public static Order responseUserStartCommand() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : " + START.getValue());
        System.out.println("> 게임 종료 : " + END.getValue());
        System.out.println("> 게임 점수 : " + STATUS.getValue());
        System.out.println("> 게임 이동 : " + MOVE.getValue() + " source위치 target위치 - 예. move b2 b3");
        final String input = sc.nextLine();
        validateNullCheck(input);
        Order.validateStartOrder(input);

        if (OrderCase.checkStart(input)) {
            return Order.of(START);
        }
        return Order.of(ELSE);
    }

    private static void validateNullCheck(final String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 게임 명령에 공백을 입력할 수 없습니다.");
        }
    }

    public static Order responseUserCommand() {
        String input = sc.nextLine();
        validateNullCheck(input);
        if (OrderCase.checkEnd(input)){
            return Order.of(END);
        }
        if (OrderCase.checkStatus(input)){
            return Order.of(STATUS);
        }
        if (OrderCase.checkMove(input)){
            return Order.of(MOVE, input);
        }
        return Order.of(ELSE);
    }
}
