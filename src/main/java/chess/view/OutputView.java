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
        System.out.printf("%s팀의 차례입니다.%n", findTurn(board));
        for (int i = 7; i >= 0; i--) {
            String rankLine = board.getRank(i).getPieces().stream()
                    .map(Piece::getSignature)
                    .collect(Collectors.joining()) + " | " + (i + 1);

            System.out.println(rankLine);
        }
        System.out.println("--------");
        System.out.println("abcdefgh");
        System.out.println();
    }

    private static String findTurn(Board board) {
        if (board.isBlackTurn()) {
            return "흑";
        }
        return "백";
    }

    public static void printResult(Board board) {
        System.out.printf("승자 : %s%n", board.findWinner().getName());
        System.out.printf("백팀 점수 : %.1f%n", board.getBlackScore());
        System.out.printf("흑팀 점수 : %.1f%n", board.getWhiteScore());
    }
}
