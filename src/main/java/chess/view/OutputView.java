package chess.view;

import chess.domain.dto.BoardDto;
import java.util.List;

public class OutputView {

    public static void printBoard(BoardDto boardDto) {
        List<List<String>> rawBoard = boardDto.board();

        for (int i = 7; i >= 0; i--) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < 8; j++) {
                stringBuilder.append(rawBoard.get(i).get(j));
            }
            System.out.println(stringBuilder);
        }
    }

    public static void printChessGameStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
    }

    public static void printCommandGuideMessage() {
        System.out.printf("> 게임 시작 : start%n"
                + "> 게임 종료 : end%n"
                + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3%n");
    }

    public static void printInputAgainMessage() {
        System.out.println("잘못된 명령어입니다.");
    }

    public static void printWrongMovementMessage() {
        System.out.println("잘못된 움직임입니다.");
    }
}
