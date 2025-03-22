package chess;

import chess.piece.Piece;

import java.util.Map;
import java.util.Scanner;

public class ChessApplication {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Board board2 = new Board();
        while (true) {
            int i = 8;
            Map<Position, Piece> board = board2.getBoard();
            for (Row row : Row.values()) {
                System.out.print(i-- + " ");
                for (Column column : Column.values()) {
                    Piece piece = board.get(new Position(row, column));
                    System.out.printf("%s%2s%s",
                            getColor(piece.getColor()),
                            piece.getName(),
                            "\u001B[0m");
                }
                System.out.println();
            }
            System.out.printf("-------------------\n");
            System.out.print(0 + " ");
            for (int j = 0; j < 8; j++) {
                System.out.printf("%2s", (char) ('A' + j));
            }
            System.out.println();
            System.out.println("이동할 기물 좌표 입력");
            Position beforePosition = getPosition();
            System.out.println("이동할 위치 입력");
            Position afterPosition = getPosition();
            board2.move(beforePosition, afterPosition);

        }
    }

    public static String getColor(Color color) {
        return switch (color) {
            case BLACK -> "\u001B[31m";
            case WHITE -> "\u001B[34m";
            default -> "\u001B[32m";
        };
    }

    public static Position getPosition() {
        System.out.println("Column, Row 입력 (ex: A3)");
        String input = scanner.nextLine();
        return new Position(getRow(input.substring(1, 2)), getColumn(input.substring(0, 1)));
    }

    public static Row getRow(String srow) {
        return switch (srow) {
            case "1" -> Row.ONE;
            case "2" -> Row.TWO;
            case "3" -> Row.THREE;
            case "4" -> Row.FOUR;
            case "5" -> Row.FIVE;
            case "6" -> Row.SIX;
            case "7" -> Row.SEVEN;
            case "8" -> Row.EIGHT;
            default -> null;
        };
    }

    public static Column getColumn(String scolumn) {
        if ("A".equals(scolumn)) {
            return Column.A;
        }
        if ("B".equals(scolumn)) {
            return Column.B;
        }
        if ("C".equals(scolumn)) {
            return Column.C;
        }
        if ("D".equals(scolumn)) {
            return Column.D;
        }
        if ("E".equals(scolumn)) {
            return Column.E;
        }
        if ("F".equals(scolumn)) {
            return Column.F;
        }
        if ("G".equals(scolumn)) {
            return Column.G;
        }
        if ("H".equals(scolumn)) {
            return Column.H;
        }
        return null;
    }
}
