package view;

import domain.chessboard.ChessBoard;
import domain.chessboard.EmptyType;
import domain.chessboard.GameResult;
import domain.chessboard.Rank;
import domain.chessboard.Result;
import domain.chessboard.Score;
import domain.chessboard.Square;
import domain.chessboard.StatusResult;
import domain.piece.Color;

import java.util.List;

public class OutputView {

    public static void printStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printErrorMessage(String message) {
        System.out.printf("[ERROR] : %s\n", message);
    }

    public static void printChessBoard(ChessBoard chessBoard) {
        List<Rank> ranks = chessBoard.getChessBoard();

        for (Rank rank : ranks) {
            printRank(rank);
        }
    }

    private static void printRank(final Rank rank) {
        for (Square square : rank.getRank()) {
            System.out.print(convertPieceToElement(square));
        }
        System.out.println();
    }

    private static String convertPieceToElement(final Square square) {
        final String elementName = ChessBoardElement.from(square.getType())
                .getElementName();

        if (square.getType() != EmptyType.EMPTY && square.isSameColor(Color.BLACK)) {
            return elementName.toUpperCase();
        }
        return elementName;
    }

    public static void printStatusResult(StatusResult statusResult) {
        printScore(statusResult.getScore());
        printResult(statusResult.getResult());
    }

    private static void printScore(Score score) {
        score.getValue()
                .forEach(OutputView::printScoreFormat);
    }

    public static void printResult(Result result) {
        result.getValue()
                .forEach(OutputView::printResultFormat);
    }

    private static void printScoreFormat(Color color, double value) {
        System.out.printf("%s : %s점\n", color, value);
    }

    private static void printResultFormat(Color color, GameResult gameResult) {
        System.out.printf("%s : %s\n", color, gameResult.getValue());
    }

}
