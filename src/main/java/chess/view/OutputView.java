package chess.view;

import chess.board.Board;
import chess.domain.piece.Piece;

import java.util.List;

public class OutputView {
    
    public static void printStart() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }
    
    public static void printBoard(Board board) {
        final List<List<Piece>> chessBoard = board.getBoard();
        for (int i = chessBoard.size()-1; i >= 0; i--) {
            for (Piece piece : chessBoard.get(i)) {
                System.out.print(piece.getSymbol());
            }
            System.out.println();
        }
    }
}
