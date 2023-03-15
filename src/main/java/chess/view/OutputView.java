package chess.view;

public class OutputView {
    public static void printGameStartMessage() {
        printMessage("체스 게임을 시작합니다.");
    }

    public static void printGameCommandInputMessage() {
        printMessage("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    private static void printMessage(String message) {
        System.out.println(message);
    }
}
