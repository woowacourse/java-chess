package chess.view;

import chess.common.IndexCommand;
import chess.domain.board.position.PositionCache;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String INPUT_COMMAND_DELIM = " ";

    public static String readStartCommand() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");

        final String command = scanner.nextLine();

        if (Command.isNotAppropriate(command)) {
            throw new IllegalArgumentException("start 나 end 둘 중에 하나 입력해주세요.");
        }

        return command;
    }

    public static List<String> readMoveCommand() {
        final List<String> commands = Arrays.stream(scanner.nextLine().split(INPUT_COMMAND_DELIM))
                                            .collect(Collectors.toList());

        if (Command.isStatus(commands.get(IndexCommand.START_COMMAND_INDEX.value()))) {
            return commands;
        }

        if (commands.size() > 3) {
            throw new IllegalArgumentException("[명령어] [시작 위치] [도착 위치] 로 입력해주세요.");
        }

        final String sourcePosition = commands.get(IndexCommand.SOURCE_POSITION.value());
        final String targetPosition = commands.get(IndexCommand.TARGET_POSITION.value());

        if (PositionCache.isNotCaching(sourcePosition) || PositionCache.isNotCaching(targetPosition)) {
            throw new IllegalArgumentException("접근할 수 없는 위치입니다.");
        }

        return commands;
    }
}
