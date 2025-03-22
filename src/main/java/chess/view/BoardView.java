package chess.view;

import chess.board.Board;
import chess.board.Color;
import chess.position.Column;
import chess.position.Position;
import chess.position.Row;

public final class BoardView {

    public void display(Board board) {
        System.out.println();
        for (Row row : Row.values()) {
            for (Column column : Column.values()) {
                Position position = new Position(row, column);
                Color color = board.colorAt(position);
                if (color == Color.EMPTY) {
                    System.out.print(".");
                } else {
                    System.out.printf("%c", PieceNotation.of(color, board.pieceTypeAt(position)));
                }
            }
            System.out.println();
        }
        System.out.print("> ");
    }
}
