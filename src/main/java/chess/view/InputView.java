package chess.view;

import static chess.view.GameCommand.isStart;

import chess.domain.Positions;
import chess.exception.InvalidCommandException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void inputStartCommand() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : %s".formatted(GameCommand.START.getValue()));
        System.out.println("> 게임 종료 : %s".formatted(GameCommand.END.getValue()));
        System.out.println("> 게임 이동 : %s source위치 target위치 - 예. %s b2 b3"
                .formatted(GameCommand.MOVE.getValue(), GameCommand.MOVE.getValue()));

        if (!isStart(SCANNER.nextLine())) {
            throw new InvalidCommandException("게임 시작 전, 게임 시작 명령어가 아닌 다른 명령어를 입력할 수 없습니다.");
        }
    }

    public static boolean isInputMove() {
        String commandValue = SCANNER.next();
        return switch (GameCommand.find(commandValue)) {
            case START -> throw new InvalidCommandException("게임이 시작한 이후, 다시 게임을 시작할 수 없습니다.");
            case END -> false;
            case MOVE -> true;
        };
    }

    public static Positions inputPositions() {
        StringTokenizer inputTokenizer = new StringTokenizer(SCANNER.nextLine());
        if (inputTokenizer.countTokens() == 2) {
            return new Positions(
                    PositionCommand.generate(inputTokenizer.nextToken()),
                    PositionCommand.generate(inputTokenizer.nextToken()));
        }
        throw new InvalidCommandException("잘못된 명령어입니다.");
    }
}
