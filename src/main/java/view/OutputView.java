package view;

import domain.ChessColumn;
import domain.ChessGame;
import domain.Rank;
import domain.Square;
import domain.piece.TeamColor;
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

    public static void printStatus(ChessGame chessGame) {
        double blackScore = chessGame.getScore(TeamColor.BLACK);
        double whiteScore = chessGame.getScore(TeamColor.WHITE);

        System.out.printf("흑의 점수는 = %s 입니다. \n백의 점수는 = %s 입니다.\n", blackScore, whiteScore);
    }

    public static void printResult(TeamColor teamColor) {
        if (teamColor == TeamColor.BLACK) {
            System.out.println("흑이 승리 후, 게임이 종료되었습니다.");
        }
        if (teamColor == TeamColor.WHITE) {
            System.out.println("백이 승리 후, 게임이 종료되었습니다.");
        }
    }
}
