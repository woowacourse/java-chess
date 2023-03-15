package chess.view;

public class OutputView {
    public static void printGameStartMessage() {
        printMessage("체스 게임을 시작합니다.");
    }

    private static void printMessage(String message) {
        System.out.println(message);
    }
}
