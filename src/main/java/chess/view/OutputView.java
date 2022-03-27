package chess.view;

import chess.domain.Board;
import chess.domain.Location;
import chess.domain.piece.Piece;
import java.util.Map;

public class OutputView {

    public static void printChessCommandInfo() {
        System.out.println("> 체스 게임을 시작합니다.\n"
                + "> 게임 시작 : start\n"
                + "> 게임 종료 : end\n"
                + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printErrorMessage(Exception exception) {
        System.out.println(exception.getMessage());
    }

    public static void printChessBoard(Board board) {
        int count = 0;
        Map<Location, Piece> boardMap = board.getBoard();
        for(Location location : boardMap.keySet()) {
            if (count < 8) {
                System.out.print(boardMap.get(location));
                count++;
                continue;
            }
            count %= 8;
            count++;
            System.out.println();
            System.out.print(boardMap.get(location));
        }
    }

}
