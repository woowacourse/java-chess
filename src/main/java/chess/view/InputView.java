package chess.view;

import chess.domain.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void inputStartCommand() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : %s".formatted(Command.START.getValue()));
        System.out.println("> 게임 종료 : %s".formatted(Command.END.getValue()));
        System.out.println("> 게임 이동 : %s source위치 target위치 - 예. %s b2 b3"
                .formatted(Command.MOVE.getValue(), Command.MOVE.getValue()));

        String commandValue = SCANNER.nextLine();
        if (Command.START != Command.find(commandValue)) {
            throw new IllegalArgumentException("게임 시작 전, 다른 명령어를 입력할 수 없습니다.");
        }
    }

    public static List<Position> inputNextCommand() {
        String input = SCANNER.nextLine();
        StringTokenizer inputTokenizer = new StringTokenizer(input);
        String commandValue = inputTokenizer.nextToken();
        return switch (Command.find(commandValue)) {
            case START -> throw new IllegalArgumentException("게임이 시작한 이후, 다시 게임을 시작할 수 없습니다.");
            case END -> new ArrayList<>();
            case MOVE -> findOldAndNewPositions(inputTokenizer);
        };
    }

    public static List<Position> findOldAndNewPositions(StringTokenizer inputTokenizer) {
        List<Position> positions = new ArrayList<>();
        if (inputTokenizer.countTokens() == 2) {
            positions.add(PositionConverter.generate(inputTokenizer.nextToken()));
            positions.add(PositionConverter.generate(inputTokenizer.nextToken()));
        }
        return positions;
    }
}
