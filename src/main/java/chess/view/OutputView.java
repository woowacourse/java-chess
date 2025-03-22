package chess.view;

import chess.Color;
import chess.Column;
import chess.Piece;
import chess.Position;
import chess.Row;
import java.util.Map;

public class OutputView {

    public void displayBoard(Map<Position, Piece> board) {
        Row[] rows = Row.values();
        Column[] columns = Column.values();

        for (int i = rows.length - 1; i >= 0; i--) {
            for (Column column : columns) {
                Position position = new Position(column, rows[i]);
                String pieceType = "_";
                if (board.containsKey(position)) {
                    Piece boardPiece = board.get(position);
                    pieceType = boardPiece.getPieceType().getDisplay();
                    if (boardPiece.getColor() == Color.WHITE) {
                        pieceType = pieceType.toUpperCase();
                    } else {
                        pieceType = pieceType.toLowerCase();
                    }
                }
                System.out.print(pieceType + " ");
            }
            System.out.println();
        }
    }

}
