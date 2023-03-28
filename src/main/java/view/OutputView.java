package view;

import domain.Board;
import domain.Location;
import domain.type.Color;

public final class OutputView {

    public void printBoard(final Board board) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (int row = 8; row >= 1; row--) {
            for (int column = 1; column <= 8; column++) {
                final String sign = PieceView.findSign(board.findPiece(Location.of(column, row)));
                stringBuilder.append(sign);
            }
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder);
    }

    public void printScore(final double whiteScore, final double blackScore) {
        System.out.println("흰색 진영의 점수 : " + whiteScore);
        System.out.println("검은색 진영의 점수 : " + blackScore);
        System.out.println();
    }

    public void printResult(final Color color) {
        if (color.equals(Color.WHITE)) {
            System.out.println("흰색 진영 승리");
            return;
        }
        if (color.equals(Color.BLACK)) {
            System.out.println("검은색 진영 승리");
            return;
        }
        System.out.println("무승부");
    }
}
