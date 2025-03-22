package chess.view;

import chess.Board;
import chess.Color;
import chess.Column;
import chess.Position;
import chess.Row;
import chess.piece.Piece;

public class OutputView {

    public static final String black    = "\u001B[30m";
    public static final String white     = "\u001B[37m";
    public static final String exit     = "\u001B[0m";

    public void printBoard(Board board) {
        CustomStringBuilder stringBuilder = new CustomStringBuilder();
        stringBuilder.appendCell(" ");
        for (Column column : Column.values()) {
            stringBuilder.appendCell(column.name());
            stringBuilder.append(" ");
        }
        stringBuilder.appendLine("");
        for (Row row : Row.values()) {
            stringBuilder.appendCell(row.getRowName());
            for (Column column : Column.values()) {
                Position position = new Position(row, column);
                if (!board.existPiece(position)) {
                    stringBuilder.appendCell("- ");
                    continue;
                }
                Piece piece = board.findPiece(position);
                stringBuilder.appendCell(convertContentColor(piece));
            }
            stringBuilder.appendLine("");
        }
        stringBuilder.print();
    }

    private String convertContentColor(Piece piece) {
        if (piece.getColor() == Color.WHITE) {
            return String.format("%s%s%s", white, piece.toString(), exit);
        }
        return String.format("%s%s%s", black, piece.toString(), exit);
    }
}
