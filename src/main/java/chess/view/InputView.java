package chess.view;

import static chess.view.GameCommand.END;
import static chess.view.GameCommand.MOVE;
import static chess.view.GameCommand.START;
import static chess.view.GameCommand.STATUS;
import static chess.view.GameCommand.isStart;

import chess.dto.CommandDto;
import chess.exception.InvalidCommandException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void inputStartCommand() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : " + START.getValue());
        System.out.println("> 게임 종료 : " + END.getValue());
        System.out.println("> 게임 이동 : %s source위치 target위치 - 예. %s b2 b3"
                .formatted(MOVE.getValue(), MOVE.getValue()));

        if (!isStart(SCANNER.nextLine())) {
            throw new InvalidCommandException("게임 시작 전, 게임 시작 명령어가 아닌 다른 명령어를 입력할 수 없습니다.");
        }
    }

    public static CommandDto inputCommand() {
        StringTokenizer inputTokenizer = new StringTokenizer(SCANNER.nextLine());
        if (!inputTokenizer.hasMoreTokens()) {
            throw new InvalidCommandException("입력이 존재하지 않습니다.");
        }
        return switch (GameCommand.find(inputTokenizer.nextToken())) {
            case START -> throw new InvalidCommandException("게임이 시작한 이후, 다시 게임을 시작할 수 없습니다.");
            case END -> new CommandDto(END, "", "");
            case MOVE -> inputMovement(inputTokenizer);
            case STATUS -> new CommandDto(STATUS, "", "");
        };
    }

    private static CommandDto inputMovement(StringTokenizer inputTokenizer) {
        if (inputTokenizer.countTokens() == 2) {
            return new CommandDto(MOVE, inputTokenizer.nextToken(), inputTokenizer.nextToken());
        }
        throw new InvalidCommandException("잘못된 명령어입니다.");
    }
}
