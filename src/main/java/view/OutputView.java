package view;

import domain.board.Board;
import domain.piece.Piece;
import domain.position.Position;
import java.util.Map;

public class OutputView {

    private OutputView() {
    }

    public static void printGameInformation() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printBoard(Board board) {
        Map<Position, Piece> chessBoard = board.getBoard();
        for (int row = 0; row < 8; row++) {
            printPieces(chessBoard, row);
        }
        System.out.println();
    }

    public static void printPieces(Map<Position, Piece> chessBoard, int row) {
        for (int column = 0; column < 8; column++) {
            Piece piece = chessBoard.get(new Position(row, column));
            System.out.print(piece.getName());
        }
        System.out.println();
    }

    public static void printScore(Board board) {
        System.out.println("검은색 : " + board.piecesScore(true).value() + "점");
        System.out.println("흰색 : " + board.piecesScore(false).value() + "점");
    }

}
