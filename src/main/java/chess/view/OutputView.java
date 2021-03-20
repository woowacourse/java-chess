package chess.view;

public class OutputView {

    private OutputView() {
    }

    public static void printGameStartMessage() {
        System.out.println("체스 게임을 시작합니다.\n"
            + "게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public static void printExceptionMessage(String e) {
        System.out.println(e);
    }

    public static void printBoard(char[][] board) {
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                System.out.print(board[y][x]);
            }
            System.out.println();
        }
    }
    
    public static void printStatus(double blackScore, double whiteScore) {
        System.out.printf("검은색은 %.2f점. 흰색은 %.2f점이 된다.", blackScore, whiteScore);
    }
}
