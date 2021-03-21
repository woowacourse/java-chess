package chess.view;

import chess.domain.piece.Color;
import chess.domain.game.Chess;
import chess.domain.game.SymbolBoard;

import static chess.ChessConstant.MAX_INDEX_OF_BOARD;
import static chess.ChessConstant.MIN_INDEX_OF_BOARD;

public class OutputView {
    
    public static void printStart() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }
    
    public static void printBoard(Chess chess) {
        final String[][] board = SymbolBoard.from(chess)
                                            .getBoard();
        for (int j = MAX_INDEX_OF_BOARD; j >= MIN_INDEX_OF_BOARD; j--) {
            for (int i = MIN_INDEX_OF_BOARD; i <= MAX_INDEX_OF_BOARD; i++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public static void printStatus(double blackScore, double whiteScore, Color winner) {
        System.out.println("### 진행 사항");
        System.out.println("BLACK - 점수 : " + blackScore);
        System.out.println("WHITE - 점수 : " + whiteScore);
        System.out.println("WINNER : " + winner.color());
        System.out.println();
    }
    
    public static void printMessageThatKingIsDead(Color winner) {
        System.out.printf("%s 의 킹이 죽었습니다." + System.lineSeparator(), winner.next()
                                                                           .color());
        System.out.printf("%s 의 승리!" + System.lineSeparator(), winner.color());
        
        System.out.println();
    }
}
