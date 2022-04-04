package chess.console.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public final class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String INPUT_COMMAND_MESSAGE = "> 게임 시작 : start\n"
            + "> 게임 종료 : end\n"
            + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3\n"
            + "> 게임 점수 확인 : status";
    private static final String COMMAND_DELIMITER = " ";

    public static List<String> inputCommandRequest() {
        System.out.println(INPUT_COMMAND_MESSAGE);
        String command = SCANNER.nextLine();
        return new ArrayList<>(Arrays.asList(command.split(COMMAND_DELIMITER)));
    }
}
