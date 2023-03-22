package view;

import domain.ChessGame;
import domain.ChessColumn;
import domain.Rank;
import domain.Square;
import java.util.ArrayList;
import java.util.List;

public class OutputView {

    public static final int CHESS_COLUMN_SIZE = 8;
    private final static List<Square> squares = makeSquares();

    private OutputView() {
    }

    private static List<Square> makeSquares() {
        List<Square> squares = new ArrayList<>();
        for (var rank : Rank.values()) {
            initChessColumn(squares, rank);
        }
        return squares;
    }

    private static void initChessColumn(List<Square> squares, Rank rank) {
        for (var chessColumn : ChessColumn.values()) {
            squares.add(Square.of(chessColumn, rank));
        }
    }

    public static void printChessBoard(ChessGame chessGame) {
        StringBuilder stringBuilder = new StringBuilder();

        for (Square square : squares) {
            stringBuilder.append(PieceConverter.of(chessGame.find(square)));
        }
        System.out.print(separateByNewLine(stringBuilder.toString()) + "\n");
    }

    private static String separateByNewLine(String input) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0, count = 1; i < input.length(); i++, count++) {
            stringBuilder.append(input.charAt(i));
            addNewLine(stringBuilder, count);
        }
        return stringBuilder.toString();
    }

    private static void addNewLine(StringBuilder stringBuilder, int count) {
        if (count % CHESS_COLUMN_SIZE == 0) {
            stringBuilder.append("\n");
        }
    }

    public static void printError(Exception exception) {
        System.out.printf("[ERROR]: %s\n", exception.getMessage());
    }
}
