package view;

import common.ExecuteContext;
import domain.Location;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String START = "start";
    private static final String END = "end";

    public boolean getEndIntent() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
        final String order = ExecuteContext.workWithExecuteStrategy(() -> {
            final String input = scanner.nextLine();
            checkExpectedInput(input, START, END);
            return input;
        });
        return order.equals(END);
    }

    public List<Location> getMoveLocations() {
        final String[] inputs = scanner.nextLine().split(" ");
        checkExpectedInput(inputs[0], "move");
        final Location start = makeLocation(inputs[1]);
        final Location end = makeLocation(inputs[2]);
        return List.of(start, end);
    }

    private Location makeLocation(final String input) {
        final int column = ColumnView.findColumn(input.charAt(0));
        final int row = Integer.parseInt(String.valueOf(input.charAt(1))) - 1;
        return Location.of(column, row);
    }

    private void checkExpectedInput(final String input, final String... expectedInputs) {
        checkBlank(input);
        if (Arrays.asList(expectedInputs).contains(input)) {
            return;
        }
        throw new IllegalArgumentException("잘못된 입력입니다.");
    }

    private void checkBlank(final String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("빈 값은 허용되지 않습니다.");
        }
    }
}
