package chess.view;

import chess.domain.state.BoardState;
import chess.domain.piece.Piece;
import java.util.stream.Collectors;

public class OutputView {

    public static void printStartView() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printError(String message) {
        System.out.println(message);
    }

    public static void printBoard(BoardState boardState) {
        System.out.println();
        System.out.printf("   [ %s팀의 차례입니다 ]%n", boardState.findTurn());
        System.out.println("      Black Side");
        System.out.println("    a b c d e f g h");
        System.out.println("  ┌-----------------┐");
        for (int i = 8; i > 0; i--) {
            String rankLine = boardState.getRank(i - 1).getPieces().stream()
                    .map(Piece::getSignature)
                    .collect(Collectors.joining(" "));

            System.out.printf("%d | %s | %d%n", i, rankLine, i);
        }
        System.out.println("  └-----------------┘");
        System.out.println("    a b c d e f g h");
        System.out.println("      White Side");
        System.out.println();
    }

    public static void printResult(BoardState boardState) {
        System.out.printf("승자 : %s%n", boardState.findWinner().getName());
        System.out.printf("흑팀 점수 : %.1f%n", boardState.calculateBlackScore());
        System.out.printf("백팀 점수 : %.1f%n", boardState.calculateWhiteScore());
    }
}
