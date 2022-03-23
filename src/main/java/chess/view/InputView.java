package chess.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String CHESS_GAME_INIT_MESSAGE = "체스 게임을 시작합니다.";
    private static final String INPUT_START_OR_END_MESSAGE = "게임 시작은 start, 종료는 end 명령을 입력하세요.";

    public static InputOption inputStartOrEnd() {
        System.out.println(CHESS_GAME_INIT_MESSAGE);
        System.out.println(INPUT_START_OR_END_MESSAGE);
        return inputOption();
    }

    private static InputOption inputOption() {
        return InputOption.from(SCANNER.nextLine());
    }
}
