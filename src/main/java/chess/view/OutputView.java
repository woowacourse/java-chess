package chess.view;

import chess.domain.board.LineDto;
import chess.domain.piece.team.Color;

import java.util.List;

public class OutputView {
    public static void printCommandNotice() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printBoard(List<LineDto> lineDtos) {
        for (LineDto lineDto : lineDtos) {
            for (String piece : lineDto.getPieces()) {
                System.out.print(piece);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printErrorException(String message) {
        System.out.println(message);
        System.out.println();
    }

    public static void printFinishWithReason(String message) {
        System.out.println(message);
    }

    public static void printWinner(Color winColor) {
        System.out.println(winColor.name() + "의 승리입니다.");
    }

    public static void printScoreStatus(double totalWhiteScore, double totalBlackScore) {
        System.out.println(Color.BLACK + " : " + totalBlackScore);
        System.out.println(Color.WHITE + " : " + totalWhiteScore);
    }
}
