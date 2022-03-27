package chess.view;

import java.util.Locale;
import java.util.Map;

import chess.domain.Status;
import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.domain.piece.Color;
import chess.domain.piece.EmptySpace;
import chess.domain.piece.Piece;

public class OutputView {

    public static void printStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
        System.out.println("> 현재 스코어 확인: status");
    }

    public static void printErrorMessage(final String message) {
        System.out.println(message);
    }

    public static void printBoard(final Map<Position, Piece> pieces) {
        for (int i = 8; i >= 1; i--) {
            printRow(pieces, i);
            System.out.print(System.lineSeparator());
        }
    }

    private static void printRow(final Map<Position, Piece> pieces, int rawRow) {
        for (int i = 1; i <= 8; i++) {
            Position position = new Position(Row.from(rawRow), Column.from(i));
            Piece piece = pieces.getOrDefault(position, new EmptySpace());
            System.out.print(convertToSymbol(piece));
        }
    }

    private static String convertToSymbol(final Piece piece) {
        final String symbol = piece.getPieceType().getSymbol();
        if (piece.getColor() == Color.BLACK) {
            return symbol.toUpperCase(Locale.ROOT);
        }
        return symbol;
    }

    public static void printStatus(Status status) {
        final double blackScore = status.getBlackScore();
        final double whiteScore = status.getWhiteScore();
        if (blackScore == -1.0 || whiteScore == -1.0) {
            printWinner(blackScore);
            return;
        }
        printCurrentScore(blackScore, whiteScore);
    }

    private static void printCurrentScore(double blackScore, double whiteScore) {
        System.out.println("현재 까지의 스코어:");
        System.out.println("=======================");
        System.out.println("블랙 스코어: " + blackScore);
        System.out.println("화이트 스코어: " + whiteScore);
    }

    private static void printWinner(double blackScore) {
        if (blackScore == -1.0) {
            System.out.println("화이트의 승리입니다");
        }
        System.out.println("블랙의 승리입니다");
    }

}
