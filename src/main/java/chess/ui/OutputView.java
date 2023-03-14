package chess.ui;

public class OutputView {

    private static final String START_CHESS_GAME = "체스 게임을 시작합니다.";
    private static final String INPUT_GAME_START_COMMAND = "게임 시작은 start, 종료는 end 명령을 입력하세요.";

    public static void printStartGame() {
        System.out.println(START_CHESS_GAME);
        System.out.println(INPUT_GAME_START_COMMAND);
    }

}
