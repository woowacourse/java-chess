package chess.view;

import chess.board.dto.BoardDto;
import java.util.List;

public class OutputView {
    public static void printGameStartMessage() {
        printMessage("체스 게임을 시작합니다.");
    }

    public static void printGameCommandInputMessage() {
        printMessage("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public static void printBoard(BoardDto boardDto) {
        final List<List<String>> nameBoard = boardDto.getNameBoard();

        for (List<String> names : nameBoard) {
            final String rowNames = String.join("", names);
            System.out.println(rowNames);
        }
    }

    private static void printMessage(String message) {
        System.out.println(message);
    }
}
