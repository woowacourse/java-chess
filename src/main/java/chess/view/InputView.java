package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String START_COMMAND = "start";
    private static final String END_COMMAND = "end";
    private static final String INPUT_COMMAND_DELIM = " ";

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
        List<String> commands = Arrays.stream(command.split(INPUT_COMMAND_DELIM))
                                      .collect(Collectors.toList());

        if (!commands.get(0).equals("move")) {
            throw new IllegalArgumentException("move 단어가 아니면 움직일 수 없습니다.");
        }

        return List.of(commands.get(1), commands.get(2));
    }
}
