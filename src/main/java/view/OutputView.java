package view;

import domain.File;
import domain.Rank;
import domain.Square;
import domain.pieceType.Piece;
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

    public void printHeader() {
        System.out.println("체스 게임을 시작합니다.\n"
                + "게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }
}
