package chess.view;

public class OutputView {

    private static String NEXT_LINE = System.lineSeparator();

    public static void printInitMessage() {
        System.out.println("체스 게임을 시작합니다.");
    }

    public static void printChessGameBoard() {
        String pieces =
            "RNBQKBNR" + NEXT_LINE
                + "PPPPPPPP" + NEXT_LINE
                + "........" + NEXT_LINE
                + "........" + NEXT_LINE
                + "........" + NEXT_LINE
                + "........" + NEXT_LINE
                + "pppppppp" + NEXT_LINE
                + "rnbqkbnr";
        System.out.println(pieces);
    }
}
