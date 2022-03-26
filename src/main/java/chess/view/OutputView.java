package chess.view;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import java.util.stream.Collectors;

public class OutputView {

    public static void printStartView() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printBoard(Board board) {
        System.out.println();
        System.out.printf("   [ %s팀의 차례입니다 ]%n", findTurn(board));
        System.out.println("흑  a b c d e f g h");
        System.out.println("  ┌-----------------┐");
        for (int i = 8; i > 0; i--) {
            String rankLine = board.getRank(i - 1).getPieces().stream()
                    .map(Piece::getSignature)
                    .collect(Collectors.joining(" "));

            System.out.printf("%d | %s | %d%n", i, rankLine, i);
        }
        System.out.println("  └-----------------┘");
        System.out.println("백  a b c d e f g h");
        System.out.println();
    }

    private static String findTurn(Board board) {
        if (board.isBlackTurn()) {
            return "흑";
        }
        return "백";
    }

    public static void printScore(Board board) {
        System.out.printf("백팀 점수 : %.1f%n", board.getWhiteScore());
        System.out.printf("흑팀 점수 : %.1f%n", board.getBlackScore());
    }

    public static void printResult(Board board) {
        System.out.printf("승자 : %s%n", board.findWinner().getName());
        System.out.printf("백팀 점수 : %.1f%n", board.getWhiteScore());
        System.out.printf("흑팀 점수 : %.1f%n", board.getBlackScore());
    }
}
