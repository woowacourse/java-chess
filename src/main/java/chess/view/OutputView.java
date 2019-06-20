package chess.view;

import chess.domain.*;

public class OutputView {
//    public static void start() {
//        System.out.println("체스 게임을 시작합니다.");
//    }
//
//    public static void command() {
//        System.out.println("게임 시작 : start");
//        System.out.println("게임 종료 : end");
//        System.out.println("게임 이동 : move source위치 target위치 - 예. move b2 b3");
//    }
//
//    public static void board(ChessBoard chessBoard) {
//        Board board = chessBoard.getBoard();
//        for (int i = 8; i > 0; i--) {
//            for(int j = 1; j <= 8; j++) {
//                System.out.print(board.boardAt(new Position(new Coordinate(j), new Coordinate(i))));
//            }
//            System.out.println();
//        }
//    }
//
//    public static void result(ChessBoard chessBoard) {
//        double whiteScore = chessBoard.totalScore(Team.WHITE);
//        double blackScore = chessBoard.totalScore(Team.BLACK);
//        System.out.println("WHITE 팀의 점수는 " + whiteScore + "점 입니다.");
//        System.out.println("BLACK 팀의 점수는 " + blackScore + "점 입니다.");
//        System.out.println("승자는 "+ getWinner(whiteScore, blackScore) + "팀 입니다.");
//    }
//
//    private static String getWinner(double whiteScore, double blackScore) {
//        return whiteScore > blackScore ? "WHITE" : "BLACK";
//    }
}
