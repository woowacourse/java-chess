package chess.domain.view;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String START_COMMAND = "start";
    private static final String END_COMMAND = "end";
    public static final String DELIMITER = " ";
    public static final int LIMIT = -1;

    public static List<String> readCommand() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 점수 계산 : status");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
        return Arrays.stream(scanner.nextLine().split(DELIMITER, LIMIT))
                .map(String::trim)
                .collect(toList());
    }

    public static List<String> readPositions() {
        final List<String> positions = Arrays.stream(scanner.nextLine()
                        .split(" ", -1))
                .map(String::trim)
                .collect(toList());
        validatePositionsSize(positions);
        return positions;
    }

    private static void validatePositionsSize(final List<String> positions) {
        if (positions.size() != 2) {
            throw new IllegalArgumentException();
        }
    }

    public static String readStartCommand() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");

        final String command = scanner.nextLine();

        if (!command.equals(START_COMMAND) && !command.equals(END_COMMAND)) {
            throw new IllegalArgumentException("start 나 end 둘 중에 하나 입력해주세요.");
        }

        return command;
    }

    public static List<String> readMoveCommand() {
        String command = scanner.nextLine();
        List<String> commands = Arrays.stream(command.split(" "))
                .collect(toList());

        if (!commands.get(0).equals("move")) {
            throw new IllegalArgumentException("명령어 move 아님");
        }

        return List.of(commands.get(1), commands.get(2));
    }
}
