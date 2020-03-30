package chess.view;

import chess.validation.Validation;

import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String INPUT_MESSAGE_GAME_START = "체스 게임을 시작합니다.\n게임 시작은 start, 종료 end 명령을 입력하세요";
    private static final String INPUT_MESSAGE_MOVE_COMMAND = "\n게임 이동 : move source위치 target위치 - 예 : move b2 b3";

    private static final Scanner scanner = new Scanner(System.in);

    public static String inputGameState() {
        System.out.println(INPUT_MESSAGE_GAME_START);
        return scanner.nextLine();
    }

    public static List<String> inputMoveCommand() {
        System.out.println(INPUT_MESSAGE_MOVE_COMMAND);
        return Validation.validateInputMoveCommand(scanner.nextLine());
    }
}
