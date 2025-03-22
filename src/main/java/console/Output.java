package console;

import chess.board.Column;
import chess.board.Position;
import chess.board.Row;
import chess.piece.Piece;
import chess.piece.Pieces;
import console.util.ColorOutput;

public class Output {

    public void start() {
        System.out.println("체스 게임을 시작합니다!!");
        System.out.println();
    }

    public void display(Pieces pieces) {
        int rowCount = 1;
        for (Row row : Row.values()) {
            System.out.print(rowCount + " ");
            rowCount++;
            for (Column column : Column.values()) {
                Piece piece = pieces.get(new Position(column, row));
                System.out.print(ColorOutput.apply(piece.color(), piece.symbol()));
            }
            System.out.println();
        }
        System.out.println("  abcdefgh");
    }
}
