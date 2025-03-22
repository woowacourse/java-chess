package chess.view;

import chess.Column;
import chess.Position;
import chess.Row;
import chess.piece.Piece;
import java.util.Map;

public class OutputView {

    public void printBoard(Map<Position, Piece> positionToPiece) {
        for (Row row : Row.values()) {
            System.out.print(row.getNumber() + " ");
            for (Column column : Column.values()) {
                Piece piece = positionToPiece.get(new Position(row, column));
                System.out.print(piece.getPieceType().getTeam(piece.getColor()));
            }
            System.out.println();
        }
        System.out.println("  ＡＢＣＤＥＦＧＨ");
    }
}
