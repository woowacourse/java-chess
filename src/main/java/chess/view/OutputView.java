package chess.view;

import chess.domain.board.Board;
import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.domain.judge.Judge;
import chess.domain.piece.Piece;
import chess.domain.piece.Side;

public class OutputView {

    public static void instruction() {
        System.out.println(String.join("\n",
            "> 체스 게임을 시작합니다.",
            "> 게임 시작 : start",
            "> 게임 종료 : end",
            "> 게임 이동 : move source위치 target위치 - 예. move b2 b3"
        ));
    }

    public static void quit() {
        System.out.println("게임을 종료합니다.");
        System.exit(0);
    }

    public static void showBoard(final Board board) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Row row : Row.values()) {
            parseRow(board, stringBuilder, row);
        }
        System.out.println(stringBuilder.toString());
    }

    public static void parseRow(final Board board, final StringBuilder stringBuilder, final Row row) {
        for (Column column : Column.values()) {
            stringBuilder.append(parsedPiece(board.findPieceBy(Position.of(row, column))));
        }
        stringBuilder.append(System.lineSeparator());
    }

    private static String parsedPiece(final Piece piece) {
        return piece.toString();

    }

    public static void showStatus(final Judge judge) {
        System.out.println("백: " + judge.calculateScore(Side.WHITE) + "점");
        System.out.println("흑: " + judge.calculateScore(Side.BLACK) + "점");
        showWinner(judge);
    }

    private static void showWinner(final Judge judge) {
        if (judge.isDraw()) {
            System.out.println("무승부입니다.");
            return;
        }
        System.out.println(judge.winner() + "이 우세한 상황입니다!");
    }

    public static void showGameOver(final Side side) {
        System.out.println(side + "이 왕을 잡아 승리했습니다!");
        System.out.println("게임을 종료합니다.");
        System.exit(0);
    }

    public static void showError(final Exception e) {
        System.out.println(e.getMessage());
    }
}
