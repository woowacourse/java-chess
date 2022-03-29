package chess.view;

import chess.domain.GameResult;
import java.util.List;

public class OutputView {

    public static void printException(Exception e) {
        System.out.println(e.getMessage());
    }

    public static void printChessMap(final char[][] chessMap) {
        for (char[] file : chessMap) {
            printChessPiece(file);
            System.out.println();
        }
    }

    private static void printChessPiece(final char[] file) {
        for (char pieceName : file) {
            System.out.print(pieceName);
        }
    }

    public static void printResult(final List<GameResult> gameResultByPlayer) {
        for (GameResult result : gameResultByPlayer) {
            System.out.print(result.getTeamName() + ": ");
            System.out.print(result.getPlayerScore() + ", ");
            System.out.println(result.getPlayerResult());
        }
    }
}
