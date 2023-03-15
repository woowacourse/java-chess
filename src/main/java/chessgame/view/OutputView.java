package chessgame.view;

import java.util.Map;

import chessgame.domain.Board;
import chessgame.domain.piece.Piece;
import chessgame.domain.point.File;
import chessgame.domain.point.Point;
import chessgame.domain.point.Rank;

public class OutputView {

    public void printStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printChessBoard(Board board) {
        Map<Point, Piece> chessBoard = board.getBoard();

        for (Rank rank : Rank.values()) {
            for (File file : File.values()) {
                Point point = Point.of(file, rank);
                if(chessBoard.containsKey(point)){
                    System.out.print(chessBoard.get(point));
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }
}
