package chess.view;

import chess.Column;
import chess.Position;
import chess.Row;
import chess.piece.Piece;
import java.util.Map;
import java.util.Objects;

public class OutputView {

    private OutputView() {
    }

    public static void printEndPiece(final Piece piece) {
        if (!Objects.equals(piece, null)) {
            System.out.println(String.format("%s를 잡았습니다!", piece.getType().getName()));
        }
        System.out.println("이동 성공");
    }

    public static void printBoard(final Map<Position, Piece> mapView) {
        final StringBuilder boardMessage = new StringBuilder();
        for (int r = 7; r >= 0; r--) {
            final StringBuilder sb = new StringBuilder();
            for (int c = 0; c < 8; c++) {
                final Position position = new Position(Row.parseToRowByNumber(r), Column.parseToColumnByNumber(c));
                String message = "- ";
                if (mapView.get(position) != null) {
                    message = mapView.get(position).getType().getName() + " ";
                }
                sb.append(message);
            }
            boardMessage.append(sb)
                    .append(System.lineSeparator());
        }
        System.out.println(boardMessage);
    }
}
