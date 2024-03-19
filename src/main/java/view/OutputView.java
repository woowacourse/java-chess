package view;

import domain.Piece;
import domain.Square;
import java.util.Map;

public class OutputView {

    public void printChessTable(final Map<Square, Piece> chessTable) {
        final StringBuilder sb = new StringBuilder();

        for (int i = 8; i >= 1; i--) {
            for (int j = 1; j <= 8; j++) {
                final Square square = new Square(i, j);

                if (chessTable.containsKey(square)) {
                    final Piece piece = chessTable.get(square);
                    final String format = PieceTypeFormat.findFormat(piece);
                    sb.append(format);
                    continue;
                }
                sb.append(".");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
