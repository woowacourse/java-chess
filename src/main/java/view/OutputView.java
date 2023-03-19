package view;

import domain.chessboard.ChessBoard;
import domain.chessboard.Row;
import domain.chessboard.Square;
import domain.piece.Color;
import domain.type.EmptyType;

import java.util.List;

public final class OutputView {

    public static void printStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
        System.out.println("> 좌표 안내");
        System.out.println("RNBQKBNR  8 (rank 8)\n" +
                "PPPPPPPP  7\n" +
                "........  6\n" +
                "........  5\n" +
                "........  4\n" +
                "........  3\n" +
                "pppppppp  2\n" +
                "rnbqkbnr  1 (rank 1)\n" +
                "\n" +
                "abcdefgh\n");
    }

    public static void printNotice(String message) {
        System.out.println(message);
    }

    public static void printErrorMessage(String message) {
        System.out.printf("[ERROR] : %s%n", message);
    }

    public static void printChessBoard(ChessBoard chessBoard) {
        final List<Row> rows = chessBoard.getChessBoard();

        System.out.print(System.lineSeparator());
        for (Row row : rows) {
            printRow(row);
        }
        System.out.print(System.lineSeparator());
    }

    private static void printRow(final Row row) {
        final List<Square> squares = row.getRow();

        for (Square square : squares) {
            System.out.print(convertPieceToElement(square));
        }
        System.out.println();
    }

    private static String convertPieceToElement(final Square square) {
        final String elementName = ChessBoardElement.getElementName(square.getType());

        if (square.isDifferentType(EmptyType.EMPTY) && square.isSameColor(Color.BLACK)) {
            return elementName.toUpperCase();
        }
        return elementName;
    }

}
