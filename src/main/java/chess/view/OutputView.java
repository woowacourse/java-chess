package chess.view;

import chess.BoardSize;
import chess.domain.Chess;
import chess.domain.Color;
import chess.domain.board.SymbolBoardDTO;

public class OutputView {
    
    private static final String LINE_SEPARATOR = System.lineSeparator();
    
    private static final String MESSAGE_START_GAME = "> 체스 게임을 시작합니다.";
    private static final String MESSAGE_START_COMMAND = "> 게임 시작 : start";
    private static final String MESSAGE_MOVE_COMMAND = "> 기물 이동 : move source위치 target위치 - 예. move b2 b3";
    private static final String MESSAGE_STATUS_COMMAND = "> 점수 : status";
    private static final String MESSAGE_END_COMMAND = "> 게임 종료 : end";
    private static final String MESSAGE_EXIT_COMMAND = "> 프로그램 종료 : exit";
    
    private static final String MESSAGE_STATUS = "### 점수";
    private static final String MESSAGE_BLACK_SCORE = "BLACK - 점수 : ";
    private static final String MESSAGE_WHITE_SCORE = "WHITE - 점수 : ";
    
    private static final String FORMAT_KING_IS_DEAD = "%s 의 킹이 죽었습니다." + LINE_SEPARATOR;
    private static final String MESSAGE_DRAW = "BLACK과 WHITE의 점수는 동일합니다.";
    private static final String FORMAT_WINNER_AND_SCORE = "%s가 %.1f 점 차이로 이기고 있습니다." + LINE_SEPARATOR + LINE_SEPARATOR;
    private static final String FORMAT_WINNER = "%s의 승리!" + LINE_SEPARATOR + LINE_SEPARATOR;
    
    private static final String MESSAGE_CHESS_IS_ALREADY_STOPPED = "체스 경기는 종료된 상태입니다.";
    private static final String MESSAGE_END_CHESS = "체스 경기를 종료했습니다." + LINE_SEPARATOR;
    
    private static final String EXIT_GAME = "게임을 종료합니다.";
    
    private static final String ERROR_BLACK_IS_EQUAL_TO_WHITE = "BLACK과 WHITE의 점수가 같을 경우 이 메소드를 사용해서는 안됩니다.";
    
    public static void printStart() {
        System.out.println(MESSAGE_START_GAME);
        System.out.println(MESSAGE_START_COMMAND);
        System.out.println(MESSAGE_MOVE_COMMAND);
        System.out.println(MESSAGE_STATUS_COMMAND);
        System.out.println(MESSAGE_END_COMMAND);
        System.out.println(MESSAGE_EXIT_COMMAND);
    }
    
    public static void printBoard(Chess chess) {
        final String[][] board = SymbolBoardDTO.from(chess).getBoard();
        for (int j = BoardSize.BOUND.getMaxIndex(); j >= BoardSize.BOUND.getMinIndex(); j--) {
            printRankAt(board, j);
        }
        System.out.println();
    }
    
    private static void printRankAt(String[][] board, int j) {
        for (int i = BoardSize.BOUND.getMinIndex(); i <= BoardSize.BOUND.getMaxIndex(); i++) {
            System.out.print(board[i][j]);
        }
        System.out.println();
    }
    
    public static void printStatusWithRunningMessage(double blackScore, double whiteScore) {
        System.out.println(MESSAGE_STATUS);
        System.out.println(MESSAGE_BLACK_SCORE + blackScore);
        System.out.println(MESSAGE_WHITE_SCORE + whiteScore);
        
        if (blackScore == whiteScore) {
            System.out.println(MESSAGE_DRAW);
            return;
        }
        System.out.printf(FORMAT_WINNER_AND_SCORE, winner(blackScore, whiteScore), Math.abs(blackScore - whiteScore));
    }
    
    private static String winner(double blackScore, double whiteScore) {
        if (blackScore > whiteScore) {
            return "WHITE";
        }
        
        if (blackScore < whiteScore) {
            return "BLACK";
        }
        
        throw new IllegalStateException(ERROR_BLACK_IS_EQUAL_TO_WHITE);
    }
    
    public static void printStatusWithWinner(double blackScore, double whiteScore, Color winner) {
        System.out.println(MESSAGE_STATUS);
        System.out.println(MESSAGE_BLACK_SCORE + blackScore);
        System.out.println(MESSAGE_WHITE_SCORE + whiteScore);
        
        if (blackScore == whiteScore) {
            System.out.println(MESSAGE_DRAW);
            return;
        }
        System.out.printf(FORMAT_WINNER, winner.color());
    }
    
    public static void printKingIsDead(Color winner) {
        System.out.printf(FORMAT_KING_IS_DEAD, winner.next().color());
        System.out.printf(FORMAT_WINNER, winner.color());
    }
    
    public static void printGameIsStopped() {
        System.out.println(MESSAGE_END_CHESS);
    }
    
    public static void printChessIsStopping() {
        System.out.println(MESSAGE_CHESS_IS_ALREADY_STOPPED);
    }
    
    public static void printExit() {
        System.out.println(EXIT_GAME);
    }
}
