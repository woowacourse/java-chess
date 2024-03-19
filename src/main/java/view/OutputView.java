package view;

import domain.File;
import domain.Piece;
import domain.Rank;
import domain.Square;

import java.util.Map;

public class OutputView {

    public void printChessTable(final Map<Square, Piece> chessTable) {
        final StringBuilder sb = new StringBuilder();

        for (final var rank : Rank.values()) {
            for (final var file : File.values()) {
                final Square square = new Square(rank, file);

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
