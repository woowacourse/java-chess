package chess.view;

import chess.Chessboard;
import chess.piece.Piece;
import chess.position.Position;
import chess.position.PositionCache;

import java.util.Map;

public class OutputView {

    private static String START_ERROR = "[ERROR] ";

    public static void printError(String message) {
        System.out.println(START_ERROR + message);
    }

    public static void printStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printBoard(Chessboard chessboard) {
        Map<Position, Piece> board = chessboard.getBoard();
        for (int row = 0; row < 8; row++) {
            printPiece(board, row);
            System.out.println();
        }
        System.out.println();
    }

    private static void printPiece(Map<Position, Piece> board, int row) {
        for (int column = 0; column < 8; column++) {
            System.out.print(board.get(PositionCache.of(row, column)).getSymbolByColor());
        }
    }

    public static void printScore(double scoreOfBlack, double scoreOfWhite) {
        System.out.println("\n획득 점수");
        System.out.println("블랙 : " + scoreOfBlack);
        System.out.println("화이트 : " + scoreOfWhite);
    }
}
