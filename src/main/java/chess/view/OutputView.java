package chess.view;

import chess.domain.Chess;
import chess.domain.Color;
import chess.domain.board.SymbolBoard;

import static chess.ChessConstant.MAX_INDEX_OF_BOARD;
import static chess.ChessConstant.MIN_INDEX_OF_BOARD;

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
    private static final String FORMAT_WINNER = "%s 의 승리!" + LINE_SEPARATOR + LINE_SEPARATOR;
    
    private static final String MESSAGE_CHESS_IS_ALREADY_STOPPED = "체스 경기는 종료된 상태입니다.";
    private static final String MESSAGE_END_CHESS = "체스 경기를 종료했습니다." + LINE_SEPARATOR;
    
    public static void printStart() {
        System.out.println(MESSAGE_START_GAME);
        System.out.println(MESSAGE_START_COMMAND);
        System.out.println(MESSAGE_MOVE_COMMAND);
        System.out.println(MESSAGE_STATUS_COMMAND);
        System.out.println(MESSAGE_END_COMMAND);
        System.out.println(MESSAGE_EXIT_COMMAND);
    }
    
    public static void printBoard(Chess chess) {
        final String[][] board = SymbolBoard.from(chess).getBoard();
        for (int j = MAX_INDEX_OF_BOARD; j >= MIN_INDEX_OF_BOARD; j--) {
            printRankAt(board, j);
        }
        System.out.println();
    }
    
    private static void printRankAt(String[][] board, int j) {
        for (int i = MIN_INDEX_OF_BOARD; i <= MAX_INDEX_OF_BOARD; i++) {
            System.out.print(board[i][j]);
        }
        System.out.println();
    }
    
    public static void printStatus(double blackScore, double whiteScore) {
        System.out.println(MESSAGE_STATUS);
        System.out.println(MESSAGE_BLACK_SCORE + blackScore);
        System.out.println(MESSAGE_WHITE_SCORE + whiteScore);
        System.out.printf(FORMAT_WINNER, winner(blackScore, whiteScore));
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
        System.out.printf(FORMAT_KING_IS_DEAD, winner.next().color());
        System.out.printf(FORMAT_WINNER, winner.color());
    }
    
    public static void printGameIsStopped() {
        System.out.println(MESSAGE_END_CHESS);
    }
    
    public static void printChessIsStopping() {
        System.out.println(MESSAGE_CHESS_IS_ALREADY_STOPPED);
    }
}
