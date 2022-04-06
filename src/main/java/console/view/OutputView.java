package console.view;

import chess.domain.piece.Symbol;
import chess.domain.board.ChessBoard;
import chess.domain.game.Score;
import chess.domain.piece.*;
import chess.domain.position.*;
import java.util.*;

public class OutputView {

    public static final String BLANK = ".";
    private static final String NEWLINE = System.lineSeparator();

    private OutputView() {
    }

    public static void printInitChessGameMessage() {
        System.out.println("> 체스 게임을 시작합니다." + NEWLINE +
            "> 게임 시작 : start" + NEWLINE +
            "> 게임 종료 : end" + NEWLINE +
            "> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printChessBoard(ChessBoard chessBoard) {
        printChessBoard(chessBoard.getPieces());
    }

    private static void printChessBoard(Collection<Piece> pieces) {
        for (Row row : Row.reversOrderedValues()) {
            printEachColumn(pieces, row);
        }
        System.out.println();
    }

    private static void printEachColumn(Collection<Piece> pieces, Row row) {
        for (Column column : Column.orderedValues()) {
            printEachRow(pieces, row, column);
        }
        System.out.println();
    }

    private static void printEachRow(Collection<Piece> pieces, Row row, Column column) {
        Position position = new Position(column, row);
        Optional<Piece> pieceOptional = findByPosition(pieces, position);
        pieceOptional.ifPresentOrElse(
            piece -> System.out.print(pieceSymbol(piece)),
            () -> System.out.print(BLANK));
    }

    private static Optional<Piece> findByPosition(Collection<Piece> pieces, Position position) {
        return pieces.stream()
            .filter(piece -> piece.isSamePosition(position))
            .findFirst();
    }

    private static String pieceSymbol(Piece piece) {
        if (piece.getColor() == Color.WHITE) {
            return Symbol.findBySymbol(piece.getClass()).toLowerCase();
        }
        return Symbol.findBySymbol(piece.getClass());
    }

    public static void printScores(Score score) {
        System.out.printf("WHITE 점수: %s %n", score.getWhiteScore());
        System.out.printf("BLACK 점수: %s %n", score.getBlackScore());
    }

    public static void printWinner(Color winnerColor) {
        System.out.printf("%s 승리!", winnerColor);
    }

    public static void printError(String errorMessage) {
        System.out.println(errorMessage);
    }
}
