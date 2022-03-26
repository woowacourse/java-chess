package chess.view;

import chess.domain.board.Board;
import chess.view.dto.BoardDto;

public class OutputView {

    private static final String COMMAND_GUIDE_MESSAGE = "> 체스 게임을 시작합니다.%n"
            + "> 게임 시작 : start%n"
            + "> 게임 종료 : end%n"
            + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3%n";

    public static void printCommandGuide() {
        System.out.printf(COMMAND_GUIDE_MESSAGE);
    }

    public static void printBoard(Board board) {
        BoardDto boardDto = new BoardDto(board);
        System.out.println(boardDto.getBoardText());
    }
}
