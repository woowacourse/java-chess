package chess.view;

import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import chess.dto.MoveOrder;
import chess.dto.OrderOption;
import chess.dto.PromotionOrder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputView {

    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    private String readLine() {
        try {
            return READER.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public MoveOrder getOrder() {
        System.out.println(getOrderDescription());

        final String input = readLine();

        final String from = input.substring(0, 2); // 0, 1
        final String orderType = input.substring(2, 3); // 2
        final String to = input.substring(3, 5); // 3, 4

        return new MoveOrder(
                parsePosition(from),
                parsePosition(to),
                parseOrderType(orderType)
        );
    }

    private Position parsePosition(final String input) {
        final String colInput = input.substring(0, 1);
        final String rowInput = input.substring(1, 2);

        Column col = switch (colInput.toUpperCase()) {
            case "A" -> Column.A;
            case "B" -> Column.B;
            case "C" -> Column.C;
            case "D" -> Column.D;
            case "E" -> Column.E;
            case "F" -> Column.F;
            case "G" -> Column.G;
            case "H" -> Column.H;
            default -> throw new IllegalArgumentException("잘못된 입력: " + colInput);
        };

        Row row = switch (rowInput) {
            case "1" -> Row.ONE;
            case "2" -> Row.TWO;
            case "3" -> Row.THREE;
            case "4" -> Row.FOUR;
            case "5" -> Row.FIVE;
            case "6" -> Row.SIX;
            case "7" -> Row.SEVEN;
            case "8" -> Row.EIGHT;
            default -> throw new IllegalArgumentException("잘못된 입력: " + rowInput);
        };

        return new Position(row, col);
    }

    private OrderOption parseOrderType(final String orderType) {
        return switch (orderType.toUpperCase()) {
            case "M" -> OrderOption.MOVE;
            case "T" -> OrderOption.TAKE;
            case "C" -> OrderOption.CASTLING;
            default -> throw new IllegalArgumentException("잘못된 입력: " + orderType);
        };
    }

    private static String getOrderDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append("다음 동작을 입력하세요.").append("\n");
        sb.append("입력 방법 : [기물위치][행동][대상위치]").append("\n");
        sb.append("기물위치 / 대상위치 예시 : F6, D5, C3").append("\n");
        sb.append("행동 : 이동 = M, 공격 = T, 캐슬링 = C").append("\n");
        return sb.toString();
    }

    public PromotionOrder getPromotionOrder() {
        System.out.println(getPromotionDescription());

        final String input = readLine();
        return switch (input.toUpperCase()) {
            case "Q" -> PromotionOrder.QUEEN;
            case "R" -> PromotionOrder.ROOK;
            case "B" -> PromotionOrder.BISHOP;
            case "K" -> PromotionOrder.KNIGHT;
            default -> throw new IllegalArgumentException("잘못된 입력: " + input);
        };
    }

    private static String getPromotionDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append("무엇으로 프로모션 하시겠습니까?").append("\n");
        sb.append("퀸 = Q, 룩 : R, 비숍 : B, 나이트 : K").append("\n");
        return sb.toString();
    }
}
