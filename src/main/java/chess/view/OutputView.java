package chess.view;

import chess.domain.ChessBoard;
import chess.domain.Color;
import chess.domain.Column;
import chess.domain.Position;
import chess.domain.Row;
import chess.domain.piece.ChessPiece;

public class OutputView {
    public void printChessBoard(ChessBoard chessBoard) {

        System.out.print(" ");
        for (Column column : Column.values()) {
            System.out.print(parseColumn(column));
        }

        System.out.println();
        for (Row row : Row.values()) {
            System.out.print(parseRow(row));
            for (Column column : Column.values()) {
                ChessPiece piece = chessBoard.getPieceOfPosition(new Position(row, column));
                System.out.print(piece.name());
            }
            System.out.println();
        }
    }

    public void printTurnMessage(Color color) {
        System.out.println(color.name() + "의 차례입니다.");
    }

    private String parseColumn(Column column) {
        if (column == Column.A) {
            return "a";
        }
        if (column == Column.B) {
            return "b";
        }
        if (column == Column.C) {
            return "c";
        }
        if (column == Column.D) {
            return "d";
        }
        if (column == Column.E) {
            return "e";
        }
        if (column == Column.F) {
            return "f";
        }
        if (column == Column.G) {
            return "g";
        }
        if (column == Column.H) {
            return "h";
        }
        return null;
    }

    private String parseRow(Row row) {
        if (row == Row.ONE) {
            return "1";
        }
        if (row == Row.TWO) {
            return "2";
        }
        if (row == Row.THREE) {
            return "3";
        }
        if (row == Row.FOUR) {
            return "4";
        }
        if (row == Row.FIVE) {
            return "5";
        }
        if (row == Row.SIX) {
            return "6";
        }
        if (row == Row.SEVEN) {
            return "7";
        }
        if (row == Row.EIGHT) {
            return "8";
        }
        return null;
    }

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }
}
