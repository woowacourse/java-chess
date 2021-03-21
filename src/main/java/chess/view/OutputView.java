package chess.view;

import chess.domain.Chess;
import chess.domain.Color;
import chess.domain.board.SymbolBoard;

import static chess.ChessConstant.MAX_INDEX_OF_BOARD;
import static chess.ChessConstant.MIN_INDEX_OF_BOARD;

public class OutputView {
    
    public static void printStart() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 기물 이동 : move source위치 target위치 - 예. move b2 b3");
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
    
    public static void printStatus(double blackScore, double whiteScore) {
        System.out.println("### 기물 점수");
        System.out.println("BLACK - 점수 : " + blackScore);
        System.out.println("WHITE - 점수 : " + whiteScore);
        System.out.println("WINNER : " + winner(blackScore, whiteScore));
        System.out.println();
    }
    
    private static String winner(double blackScore, double whiteScore) {
        if (blackScore > whiteScore) {
            return "WHITE";
        }
        
        if (blackScore == whiteScore) {
            return "DRAW";
        }
        
        return "BLACK";
    }
    
    public static void printKingIsDead(Color winner) {
        System.out.printf("%s 의 킹이 죽었습니다." + System.lineSeparator(), winner.next()
                                                                           .color());
        System.out.printf("%s 의 승리!" + System.lineSeparator(), winner.color());
        
        System.out.println();
    }
    
    public static void printGameIsStopped() {
        System.out.println("체스 경기를 종료했습니다." + System.lineSeparator());
    }
    
    public static void printChessIsStopping() {
        System.out.println("체스 경기는 종료된 상태입니다.");
    }
}
