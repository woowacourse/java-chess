package view;

import domain.chessgame.ChessGameManager;
import domain.piece.Piece;
import domain.position.Position;
import java.util.Map;

public class OutputView {

    private OutputView() {
    }

    public static void printGameInformation() {
        System.out.println();
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printBoard(ChessGameManager chessGameManager) {
        System.out.println();
        Map<Position, Piece> chessBoard = chessGameManager.board().getBoard();
        for (int row = 0; row < 8; row++) {
            printPieces(chessBoard, row);
        }
        System.out.println();
    }

    private static void printPieces(Map<Position, Piece> chessBoard, int row) {
        for (int column = 0; column < 8; column++) {
            Piece piece = chessBoard.get(new Position(row, column));
            System.out.print(piece.getName());
        }
        System.out.println();
    }

    public static void printScore(ChessGameManager chessGameManager) {
        double blackScore = chessGameManager.board().piecesScore(true).value();
        double whiteScore = chessGameManager.board().piecesScore(false).value();
        System.out.println("검은색 : " + blackScore + "점");
        System.out.println("흰색 : " + whiteScore + "점");
        if (blackScore > whiteScore) {
            System.out.println("검은색 승리!");
            return;
        }
        if (blackScore < whiteScore) {
            System.out.println("흰색 승리!");
            return;
        }
        System.out.println("무승부!\n");
    }

    public static void printResult(ChessGameManager chessGameManager) {
        if (chessGameManager.isBlackKingAlive()
            && chessGameManager.isWhiteKingAlive()) {
            System.out.println("무승부!");
            return;
        }
        if (chessGameManager.isBlackKingAlive()) {
            System.out.println("검은색 승리!");
            return;
        }
        if (chessGameManager.isWhiteKingAlive()) {
            System.out.println("흰색 승리!");
        }
    }

}
