package chess.view;

import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import chess.dto.PieceDto;
import chess.dto.PieceType;
import java.util.List;
import java.util.Map;

public class OutputView {

    private static final List<Row> ROW_ORDER = List.of(
            Row.EIGHT, Row.SEVEN, Row.SIX, Row.FIVE, Row.FOUR, Row.THREE, Row.TWO, Row.ONE
    );
    private static final List<Column> COLUMN_ORDER = List.of(
            Column.A, Column.B, Column.C, Column.D, Column.E, Column.F, Column.G, Column.H
    );
    private static final Map<PieceType, String> PIECE_DISPLAY = Map.of(
            PieceType.KING, "K", PieceType.QUEEN, "Q", PieceType.KNIGHT, "N",
            PieceType.BISHOP, "B", PieceType.ROOK, "R", PieceType.PAWN, "P"
    );

    public void printBoard(Map<Position, PieceDto> board) {
        for (Row row : ROW_ORDER) {
            for (Column column : COLUMN_ORDER) {
                PieceDto piece = board.get(new Position(row, column));
                if (piece == null) {
                    System.out.print(".");
                    continue;
                }
                String display = PIECE_DISPLAY.get(piece.type());
                if (!piece.isBlack()) {
                    display = display.toLowerCase();
                }
                System.out.print(display);
            }
            System.out.println();
        }
    }
}
