package chess.view;

public class OutputView {
    private static final String INFO_MESSAGE_FORMAT = "> %s\n";
    private static final String START_MESSAGE = "체스 게임을 시작합니다.";
    private static final String START_COMMAND = "게임 시작 : start";
    private static final String END_COMMAND = "게임 종료 : end";
    private static final String MOVE_COMMAND = "게임 이동 : move source위치 target위치 - 예. move b2 b3";


    public static void printStartInfo() {
        System.out.printf(INFO_MESSAGE_FORMAT,START_MESSAGE);
        System.out.printf(INFO_MESSAGE_FORMAT,START_COMMAND);
        System.out.printf(INFO_MESSAGE_FORMAT,END_COMMAND);
        System.out.printf(INFO_MESSAGE_FORMAT,MOVE_COMMAND);
    }

    public static void printChessBoard() {

    }
}
