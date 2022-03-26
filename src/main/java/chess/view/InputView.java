package chess.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public final class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static List<String> inputStartOrEndGame() {
        System.out.println("> 게임 시작 : start\n"
                + "> 게임 종료 : end\n"
                + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
        String command = SCANNER.nextLine();
        checkCommandValue(command);
        return new ArrayList<>(Arrays.asList(command.split(" ")));
    }

    private static void checkCommandValue(final String inputCommand) {
        if (!inputCommand.equals("start") && !inputCommand.equals("end") && !inputCommand.contains("move") && !inputCommand.contains("status")) {
            throw new IllegalArgumentException(String.format("잘못된 게임 시작 커맨드입니다. %s", inputCommand));
        }
    }
}
