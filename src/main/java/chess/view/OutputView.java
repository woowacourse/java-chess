package chess.view;

import chess.domain.board.ChessBoard;
import chess.domain.Color;
import chess.domain.Column;
import chess.domain.Position;
import chess.domain.Row;
import chess.domain.piece.ChessPiece;

public class OutputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    public void printChessBoard(ChessBoard chessBoard) {
        System.out.print("  ");
        for (Column column : Column.values()) {
            System.out.print(parseColumn(column) + " ");
        }

        System.out.println();
        for (Row row : Row.values()) {
            System.out.print(parseRow(row) + " ");
            for (Column column : Column.values()) {
                ChessPiece piece = chessBoard.getPieceOfPosition(new Position(row, column));
                System.out.print(colorMessage(piece.name(), piece.getColor()) + " ");
            }
            System.out.println();
        }
    }

    public void printTurnMessage(Color color) {
        System.out.println(LINE_SEPARATOR + colorMessage(color.name(), color) + "의 차례입니다.");
    }

    public void printWinningMessage(Color winner) {
        System.out.println(LINE_SEPARATOR + colorMessage(winner.name(), winner) + "의 승리입니다!");
    }

    public static void printErrorMessage(String message) {
        System.out.println(message);
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

    private String colorMessage(String message, Color color) {
        if (color == Color.BLACK) {
            return "\u001B[90m" +  message +  "\u001B[0m";
        }
        if (color == Color.WHITE) {
            return "\u001B[97m" +  message +  "\u001B[0m";
        }
        return "\u001B[34m" +  message +  "\u001B[0m";
    }
}
