package console.view;

import chess.ChessBoard;
import chess.game.Score;
import chess.piece.*;
import chess.position.*;
import java.math.BigDecimal;
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

    private static void printChessBoard(List<Piece> pieces) {
        for (Rank rank : Rank.orderedValues()) {
            printEachColumn(pieces, rank);
        }
        System.out.println();
    }

    private static void printEachColumn(List<Piece> pieces, Rank rank) {
        for (File file : File.orderedValues()) {
            printEachRow(pieces, rank, file);
        }
        System.out.println();
    }

    private static void printEachRow(List<Piece> pieces, Rank rank, File file) {
        Position position = new Position(file, rank);
        Optional<Piece> pieceOptional = findByPosition(pieces, position);
        pieceOptional.ifPresentOrElse(
            piece -> System.out.print(pieceSymbol(piece)),
            () -> System.out.print(BLANK));
    }

    private static Optional<Piece> findByPosition(List<Piece> pieces, Position position) {
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
        BigDecimal whiteScore = score.getWhiteScore();
        BigDecimal blackScore = score.getBlackScore();
        System.out.printf("WHITE 점수: %s %n", whiteScore.toPlainString());
        System.out.printf("BLACK 점수: %s %n", blackScore.toPlainString());
    }

    public static void printWinner(Color winnerColor) {
        System.out.printf("%s 승리!", winnerColor);
    }

    public static void printError(String errorMessage) {
        System.out.println(errorMessage);
    }
}
