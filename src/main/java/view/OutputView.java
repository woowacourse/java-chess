package view;

import domain.Board;
import domain.Location;

public class OutputView {

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
}
