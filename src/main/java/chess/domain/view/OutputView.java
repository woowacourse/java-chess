package chess.domain.view;

import chess.domain.board.Board;
import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.domain.judge.Judge;
import chess.domain.piece.Piece;
import chess.domain.piece.Side;

import java.sql.SQLException;
import java.util.Optional;

public class OutputView {

    public static final String EMPTY_POSITION = ".";

    public static void instruction() {
        System.out.println("> 체스 게임을 시작합니다.\n"
                + "> 게임 시작 : start\n"
                + "> 게임 종료 : end\n"
                + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void quit() {
        System.out.println("게임을 종료합니다.");
    }

    public static void showBoard(Board board) throws SQLException {
        StringBuilder stringBuilder = new StringBuilder();
        for (Row row : Row.values()) {
            parseRow(board, stringBuilder, row);
        }
        System.out.println(stringBuilder.toString());
    }

    public static void parseRow(final Board board, final StringBuilder stringBuilder, final Row row) throws SQLException {
        for (Column column : Column.values()) {
            stringBuilder.append(parsedPiece(board.findPieceOn(Position.of(row, column))));
        }
        stringBuilder.append(System.lineSeparator());
    }

    private static String parsedPiece(Optional<Piece> piece) {
        if (piece.isPresent()) {
            return piece.get().toString();
        }
        return EMPTY_POSITION;
    }

    public static void showStatus(Judge judge) throws SQLException {
        System.out.println("백: " + judge.calculateScore(Side.WHITE) + "점");
        System.out.println("흑: " + judge.calculateScore(Side.BLACK) + "점");
        showWinner(judge.winner());
    }

    public static void showWinner(final Optional<Side> winner) {
        if (winner.isPresent() && winner.get() == Side.WHITE) {
            System.out.println("백이 이기고 있습니다!");
        }
        if (winner.isPresent() && winner.get() == Side.BLACK) {
            System.out.println("흑이 이기고 있습니다!");
        }
        if (!winner.isPresent()) {
            System.out.println("무승부 입니다.");
        }
    }

    public static void showGameOver(final Optional<Side> winner) {
        if (winner.isPresent() && winner.get() == Side.WHITE) {
            System.out.println("왕을 잡아서 백이 승리했습니다!");
        }
        if (winner.isPresent() && winner.get() == Side.BLACK) {
            System.out.println("왕을 잡아서 백이 승리했습니다!");
        }
        System.out.println("게임을 종료합니다.");
    }

    public static void showError(Exception e) {
        System.out.println(e.getMessage());
    }
}
