package chess.view;

import chess.domain.GameResult;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.board.Positions;
import chess.domain.piece.Piece;

import java.util.Map;

public class OutputView {
    private static final char MIN_Y_POINT = '1';
    private static final char MAX_Y_POINT = '8';
    private static final char MIN_X_POINT = 'a';
    private static final char MAX_X_POINT = 'h';

    public static void printBoard(Board board) {
        Map<Position, Piece> chessBoard = board.get();

        for (char yPoint = MAX_Y_POINT; yPoint >= MIN_Y_POINT; yPoint--) {
            for (char xPoint = MIN_X_POINT; xPoint <= MAX_X_POINT; xPoint++) {
                if (xPoint == MAX_X_POINT) {
                    System.out.println(chessBoard.get(Positions.of(xPoint, yPoint)).getName() + "   " + yPoint);
                } else {
                    System.out.print(chessBoard.get(Positions.of(xPoint, yPoint)).getName());
                }
            }
        }
        System.out.print(System.lineSeparator());
        System.out.println("abcdefgh");
    }

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }

    public static void printResult(GameResult gameResult) {
        if (gameResult.isBlackWin()) {
            System.out.println("<승자> Black <패자> White");
        }
        if (gameResult.isWhiteWin()) {
            System.out.println("<승자> White <패자> Black");
        }
        System.out.println(String.format("점수 : Black - %.1f점 / White - %.1f점", gameResult.getAliveBlackPieceScoreSum(),
                                         gameResult.getAliveWhitePieceScoreSum()));
    }
}
