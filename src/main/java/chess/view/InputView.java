package chess.view;

import chess.dto.CommandRequest;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String MOVE_COMMAND = "move";
    private static final String COMMAND_DELIMITER = " ";
    private static final int FROM_INDEX = 0;
    private static final int TO_INDEX = 0;

    public static String inputStartCommand() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("=== 게임 시작 후 가능한 명령 ===");
        System.out.println("> 게임 이동 : move source위치 target위치 - ex) move b2 b3");
        System.out.println("> 점수 출력 : status");
        System.out.println("===========================");
        return scanner.nextLine();
    }

    public static CommandRequest inputPlayCommand() {
        final String input = scanner.nextLine();
        if (input.startsWith(MOVE_COMMAND.toUpperCase())) {
            return createCommandRequest(input);
        }
        return new CommandRequest(input);
    }

    private static CommandRequest createCommandRequest(final String input) {
        final String[] split = input.split(COMMAND_DELIMITER);
        return new CommandRequest(MOVE_COMMAND, split[FROM_INDEX], split[TO_INDEX]);
    }
}
