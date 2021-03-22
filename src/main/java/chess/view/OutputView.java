package chess.view;

import chess.domain.board.LineDto;

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
}
